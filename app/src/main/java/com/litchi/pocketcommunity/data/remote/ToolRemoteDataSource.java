package com.litchi.pocketcommunity.data.remote;

import com.litchi.pocketcommunity.data.ToolDataSource;
import com.litchi.pocketcommunity.util.HttpUtils;
import com.litchi.pocketcommunity.util.UrlUtils;

import okhttp3.Callback;

public class ToolRemoteDataSource implements ToolDataSource {

    @Override
    public void getDailyImage(Callback callback) {
        HttpUtils.get(UrlUtils.url(UrlUtils.DAILY_IMAGE), callback);
    }
}
