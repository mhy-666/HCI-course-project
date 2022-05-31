package com.hci.mhy.Api;

import com.google.gson.internal.$Gson$Types;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Request;
import okhttp3.Response;

public abstract class BaseCallback<T> {
    //泛型类型，自动获取泛型类型
    Type mType;

    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    public BaseCallback() {
        mType = getSuperclassTypeParameter(getClass());
    }

    public abstract void onRequestBefore(Request request);

    //请求失败
    public abstract void onFailure(IOException e);


    public abstract void onResponse(Response response);

    //返回code正确
    public abstract void onSuccess(Response response,T t);

    //返回code错误
    public abstract void onError(Response response, int code, Exception e);

}
