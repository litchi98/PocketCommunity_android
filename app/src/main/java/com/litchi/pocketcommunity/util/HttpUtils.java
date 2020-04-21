package com.litchi.pocketcommunity.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.litchi.pocketcommunity.MyApplication;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtils {

    private static OkHttpClient okHttpClient = new OkHttpClient();

    public static void get(String url, Callback callback){
        Request.Builder builder = new Request.Builder();
        Request request = auth(builder).url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public static void post(String url, RequestBody body, Callback callback){
        Request.Builder builder = new Request.Builder();
        Request request = auth(builder).url(url).post(body).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public static void put(String url, RequestBody body, Callback callback){
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).put(body).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public static void delete(String url, RequestBody body, Callback callback){
        Request.Builder builder = new Request.Builder();
        auth(builder).url(url);
        if (body != null) {
            builder.delete(body);
        } else {
            builder.delete();
        }
        okHttpClient.newCall(builder.build()).enqueue(callback);
    }

    private static Request.Builder auth(Request.Builder builder){
        SharedPreferences data = MyApplication.getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        long expireTime = 0;
        String token = null;
        expireTime = data.getLong("expireTime", 0);
        token = data.getString("token", "");
        if (expireTime==00 && token.length()==0){
            return builder;
        }
        if (expireTime-System.currentTimeMillis()<0){
            return builder;
        }
        return builder.header("Authorization", token);
    }
}
