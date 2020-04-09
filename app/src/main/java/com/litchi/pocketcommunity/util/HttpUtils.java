package com.litchi.pocketcommunity.util;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtils {

    private static OkHttpClient okHttpClient = new OkHttpClient();

    public static void get(String url, Callback callback){
        Request request = new Request.Builder()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public static void post(String url, RequestBody body, Callback callback){
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public static void put(String url, RequestBody body, Callback callback){
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public static void delete(String url, RequestBody body, Callback callback){
        Request.Builder builder = new Request.Builder()
                .url(url);
        if (body != null) {
            builder.delete(body);
        } else {
            builder.delete();
        }
        okHttpClient.newCall(builder.build()).enqueue(callback);
    }
}
