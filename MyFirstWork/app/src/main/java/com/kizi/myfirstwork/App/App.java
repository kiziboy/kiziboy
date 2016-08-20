package com.kizi.myfirstwork.App;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import me.xiaopan.android.preference.PreferencesUtils;

/**
 * Created by ASUS on 2016/8/10.
 */
public class App extends Application {
    private static Context context;
    private static List<Activity> activities = new ArrayList<>();
    private static String Token;
    private static String userId;
    private static Boolean isLogin;
    @Override
    public void onCreate() {
        System.out.println("start Activity");
        super.onCreate();
        context = getApplicationContext();
        Logger.init("hhh").methodCount(2).hideThreadInfo().logLevel(LogLevel.FULL);
        Stetho.initialize(Stetho.newInitializerBuilder(context)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
                .build());
        String token=PreferencesUtils.getString(context,"Token","");
        String userId=PreferencesUtils.getString(context,"UserId","");
        Boolean isLogin=PreferencesUtils.getBoolean(context,"isLogin",false);
        if(isLogin){
            App.isLogin=true;
            if(!"".equals(token)&&!"".equals(userId)){
                App.Token=token;
                App.userId=userId;
            }
        }else {
            App.isLogin=false;
        }
    }
    public static Context getContext() {
        return context;
    }

    public static List<Activity> getActivities() {
        return activities;
    }

    public static void perfectExit() {
        for (Activity activity : activities) {
            activity.finish();
        }
    }
    public static void setToken(String str){
        Token=str;
    }
    public static void setUserId(String str){
        userId=str;
    }
    public static void setIsLogin(Boolean b){
        App.isLogin=b;
    }

}
