package com.kizi.myfirstwork.Http;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by ASUS on 2016/8/11.
 */
public abstract class CallBack<T> {
    public Type type;

    public CallBack() {
        type = getSuperclassTypeParameter(getClass());
    }

    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    public abstract void onSuccess(T result);

    public void onFailure(int errorType, String message) {
        //Toast.makeText(App.getContext(), message, Toast.LENGTH_SHORT).show();
        System.out.println("errorType = [" + errorType + "], message = [" + message + "]");
    }
}
