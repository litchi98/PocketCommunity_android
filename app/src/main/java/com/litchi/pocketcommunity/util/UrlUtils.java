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
    public static final String GET_USERS = "/users";

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

    // add a notice
    public static final String ADD_NOTICE = "/notice";

    /* Proposal api */
    // get done proposal
    public static final String GET_DONE_PROPOSAL = "/work-orders/done";

    // get not done proposal
    public static final String GET_UNDONE_PROPOSAL = "/work-orders/undone";

    // get proposal detail
    public static final String GET_PROPOSAL_DETAIL = "/work-order/detail";

    // transfer proposal
    public static final String PROPOSAL_TRANSFER = "/work-order/transfer";

    // add a work order
    public static final String ADD_PROPOSAL = "/work-order";

    /* Building api */
    // get building
    public static final String GET_BUILDING = "/building";

    public static String url(String api){
        return  new StringBuilder().append(BASE_URL).append(api).toString();
    }

}
