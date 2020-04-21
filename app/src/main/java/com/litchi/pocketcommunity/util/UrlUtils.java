package com.litchi.pocketcommunity.util;

public class UrlUtils {

    // server url
    public static final String BASE_URL = "http://10.0.2.2:8888";

    /* api url */

    /* Account api */
    // login
    public static final String ACCOUNT_LOGIN = "/login";

    // register
    public static final String REGISTER = "/register";

    // get all user
    public static final String ALL_USER = "/all";

    // add a user
    public static final String ADD_USER = "/add";

    /* Tool api */
    // get a image for the day
    public static final String DAILY_IMAGE = "/dailyImage";

    // get image
    public static final String GET_IMAGE = "/image";

    /* Notice api */
    // get notices by page
    public static final String GET_NOTICE = "/notices";

    /* Proposal api */
    // get done proposal
    public static final String GET_DONE_PROPOSAL = "/work-orders/done";

    // get not done proposal
    public static final String GET_UNDONE_PROPOSAL = "/work-orders/undone";

    public static String url(String api){
        return  new StringBuilder().append(BASE_URL).append(api).toString();
    }

}
