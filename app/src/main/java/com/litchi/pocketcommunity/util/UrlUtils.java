package com.litchi.pocketcommunity.util;

import android.util.Log;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class UrlUtils {

    // server url
    public static final String BASE_URL = "http://10.0.2.2:8888";

    /* api url */

    /* Account api */
    // login
    public static final String ACCOUNT_LOGIN = "/login";

    // register
    public static final String REGISTER = "/register";

    // CRUD
    public static final String CRUD = "/user";

    // get all user
    private static final String ALL_USER = "/users";

    public static String url(String api){
        return  new StringBuilder().append(BASE_URL).append(api).toString();
    }

}
