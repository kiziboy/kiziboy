package com.kizi.myfirstwork.Http;

import android.os.Handler;
import android.os.Looper;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.kizi.myfirstwork.App.App;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import me.xiaopan.android.net.NetworkUtils;
import me.xiaopan.android.preference.PreferencesUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by ASUS on 2016/8/11.
 */
public class HttpClient {
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new LoggingInterceptor())
            .addNetworkInterceptor(new StethoInterceptor())
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .build();
    /**
     * 上传多张或者单张图片
     *
     * @param tag
     * @param url
     * @param key
     * @param map
     * @param paths
     * @param callBack
     */
    public static void postImage(Object tag, String url, String key, HashMap<String, String> map, List<String> paths, final CallBack callBack) {

        if (!NetworkUtils.isConnectedByState(App.getContext())) {
            callBack.onFailure(5, "网络开小差了！！");
            return;
        }
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (String path : paths) {
            if (!"add".equals(path))
                builder.addFormDataPart(key, path, RequestBody.create(MEDIA_TYPE_PNG, new File(path)));
        }
        for (String s : map.keySet()) {
            builder.addFormDataPart(s, map.get(s));
        }
        String Cookie_token = PreferencesUtils.getString(App.getContext(), "Cookie_token", UUID.randomUUID().toString());

        RequestBody requestBody = builder.build();

        Request request = new Request.Builder()
                .tag(tag)
                .addHeader("Cookie_token", Cookie_token)
                .url(url)
                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handleError(e, callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                handleResponse(response, callBack);
            }
        });

    }
    /**
     * post请求
     *
     * @param tag
     * @param url
     * @param map
     * @param callBack
     */
    public static void post(Object tag, String url, HashMap<String, String> map, final CallBack callBack) {

        if (!NetworkUtils.isConnectedByState(App.getContext())) {
            callBack.onFailure(5, "网络开小差了！！");
            return;
        }
        FormBody.Builder builder = new FormBody.Builder();
        if (map != null) {
            Logger.json(new Gson().toJson(map));
            for (String s : map.keySet()) {
                builder.add(s, map.get(s));
            }
        }
        RequestBody requestBody = builder.build();

        String Cookie_token = PreferencesUtils.getString(App.getContext(), "Cookie_token", UUID.randomUUID().toString());


        Request request = new Request.Builder()
                .addHeader("Cookie_token", Cookie_token)
                .tag(tag)
                .url(url)
                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handleError(e, callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                handleResponse(response, callBack);

            }
        });
    }

    public static void get(Object tag, String url, HashMap<String, String> map, final CallBack callBack) {

       /* if (!NetworkUtils.isConnectedByState(App.getContext())) {
            callBack.onFailure(5, "网络开小差了！！");
            return;
        }*/
        if (map != null) {
            url += "?";
            for (String s : map.keySet()) {
                url += s + "=" + map.get(s) + "&";
            }
        }
        //String Cookie_token = PreferencesUtils.getString(App.getContext(), "Cookie_token", UUID.randomUUID().toString());

        Request request = new Request.Builder()
                .tag(tag)
                //.addHeader("Cookie_token", Cookie_token)
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handleError(e, callBack);

            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                handleResponse(response, callBack);

            }
        });

    }
    private static void handleError(final IOException e, final CallBack callBack) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                System.out.println("e.getLocalizedMessage() = " + e.getLocalizedMessage());
                callBack.onFailure(4, e.getLocalizedMessage());
            }
        });
    }
    private static void handleResponse(Response response, final CallBack callBack) throws IOException {
        String json = response.body().string();
        System.out.println("json1 = " + json);
        json = json.replace("[]", "\"\"");
        System.out.println("json = " + json);
      //  PreferencesUtils.putString(App.getContext(), "Cookie_token", response.header("cookie_token"));
        final String finalJson = json;
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {

                   // Logger.json(finalJson);
                   // JSONObject jsonObject = new JSONObject(finalJson);
                    callBack.onSuccess(new Gson().fromJson(finalJson,callBack.type));

                 /*   if (jsonObject.isNull(RETCODE)) {
                        callBack.onFailure(0, "rescode key not exists!");
                        return;
                    }
                    if (jsonObject.isNull(MESSAGE)) {
                        callBack.onFailure(0, "message key not exists!");
                        return;
                    }

                    if (jsonObject.getString(RETCODE).equals("301")) {
                        callBack.onFailure(0, jsonObject.getString(MESSAGE));
                        return;
                    }
                    if (jsonObject.getString(RETCODE).equals("305")) {
                        callBack.onFailure(3, jsonObject.getString(MESSAGE));
                        return;
                    }
                    if (jsonObject.getString(RETCODE).equals("302")) {
                        callBack.onFailure(2, jsonObject.getString(MESSAGE));
                        PreferencesUtils.putBoolean(App.getContext(), "isLogin", false);
                        Intent intent = new Intent(App.getContext(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        App.getContext().startActivity(intent);
                        App.perfectExit();
                        return;
                    }*/
                  /*  if (jsonObject.getString(RETCODE).equals("300")) {
                        if (jsonObject.isNull(RESULT)) {
                            callBack.onSuccess(jsonObject.getString(MESSAGE));
                        } else {
                            String result = jsonObject.getString(RESULT);
                            callBack.onSuccess(new Gson().fromJson(result, callBack.type));
                        }
                    }*/
                } catch (Exception e) {
                    e.printStackTrace();
                    callBack.onFailure(4, e.getLocalizedMessage());
                }
            }
        });
    }
    /**
     * 根据tag取消请求
     *
     * @param tag 标签
     */
    public static void cancelRequest(Object tag) {
        for (Call call : okHttpClient.dispatcher().queuedCalls()) {
            if (call.request().tag().equals(tag)) {
                call.cancel();
            }
        }
        for (Call call : okHttpClient.dispatcher().runningCalls()) {
            if (call.request().tag().equals(tag)) {
                call.cancel();
            }
        }
    }
    /**
     * 请求响应日志信息，方便debug
     */
    public static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
           /* Logger.i(String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));*/

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
         /*   Logger.i(String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));*/
            return response;
        }
    }
}
