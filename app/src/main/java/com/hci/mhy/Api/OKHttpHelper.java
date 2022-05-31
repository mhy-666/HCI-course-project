package com.hci.mhy.Api;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.example.uit.Constant;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OKHttpHelper {

    private static OkHttpClient okHttpClient;

    private Gson gson;

    private final Handler handler;

    private OKHttpHelper() {
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS).build();

        gson = new Gson();

        handler = new Handler(Looper.getMainLooper());
    }

    public static OKHttpHelper getInstance() {
        return new OKHttpHelper();
    }

    public void get(String url, BaseCallback callback) {
        Request request = buildRequest(Constant.BASE_URL + url, null, HttpMethodType.GET);
        doRequest(request, callback);
    }

    public void post(String url, Map<String, String> params, BaseCallback callback) {
        Request request = buildRequest(Constant.BASE_URL + url, params, HttpMethodType.POST);
        doRequest(request, callback);
    }

    public void doRequest(Request request, BaseCallback callback) {

        callback.onRequestBefore(request);

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                callback.onResponse(response);

                if (response.isSuccessful()) {
                    String resultStr = response.body().string();

                    if (callback.mType == String.class) {
                        // 当callback中需要对ui线程进行处理时，要交给handler
                        callbackSuccess(callback, response, resultStr);
                        // 如果不需要对ui线程进行操作，这样就可以了
//                        callback.onSuccess(response, resultStr);
                    } else {
                        try {
                            Object object = gson.fromJson(resultStr, callback.mType);
                            // 当callback中需要对ui线程进行处理时，要交给handler
                            callbackSuccess(callback, response, object);
                            // 如果不需要对ui线程进行操作，这样就可以了
//                            callback.onSuccess(response, object);
                        } catch (Exception e) {
                            // 当callback中需要对ui线程进行处理时，要交给handler
                            callbackError(callback, response, response.code(), e);
                            // 如果不需要对ui线程进行操作，这样就可以了
//                            callback.onError(response, response.code(), e);
                        }
                    }
                } else {
                    // 当callback中需要对ui线程进行处理时，要交给handler
                    callbackError(callback, response, response.code(), null);
                    // 如果不需要对ui线程进行操作，这样就可以了
//                    callback.onError(response, response.code(), null);
                }
            }
        });
    }


    private Request buildRequest(String url, Map<String, String> params, HttpMethodType methodType) {

        Request.Builder builder = new Request.Builder();

        builder.url(url);

        if (methodType == HttpMethodType.GET) {
            builder.get();
        } else if (methodType == HttpMethodType.POST) {
            RequestBody requestBody = buildFormData(params);
            builder.post(requestBody);
        }
        return builder.build();
    }

    private RequestBody buildFormData(Map<String, String> params) {
        FormBody.Builder formBuilder = new FormBody.Builder();

        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                formBuilder.add(entry.getKey(), entry.getValue());
            }
        }
        return formBuilder.build();
    }

    private void callbackSuccess(final BaseCallback callback, final Response response, final Object object) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(response, object);
            }
        });
    }

    private void callbackError(final BaseCallback callback, final Response response, final int code, final Exception e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(response, code, e);
            }
        });
    }

    enum HttpMethodType {
        GET,
        POST
    }
}
