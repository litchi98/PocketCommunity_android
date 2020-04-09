package com.litchi.pocketcommunity.util;

import java.util.Map;

public class ResultMessage {

    public static final String ERROR_RESULT = "500";
    public static final String SUCCESS_RESULT = "200";
    public static final String UNAUTHORIZED = "401";

    private String result;
    private String msg;
    private Map<String, Object> data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getDatas() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Object getData(String key){
        if (data != null){
            return data.get(key);
        }
        return null;
    }
}
