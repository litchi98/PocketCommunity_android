package com.litchi.pocketcommunity.util;

import com.google.gson.Gson;

public class JsonUtils {

    private static Gson gson = new Gson();

    public static String toJSON(Object obj){
        return gson.toJson(obj);
    }

    public static <T> T parseJSON(String json, Class<T> c){
        return gson.fromJson(json, c);
    }

}
