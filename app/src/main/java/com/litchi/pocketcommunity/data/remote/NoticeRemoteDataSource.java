package com.litchi.pocketcommunity.data.remote;

import com.litchi.pocketcommunity.data.NoticeDataSource;
import com.litchi.pocketcommunity.util.HttpUtils;
import com.litchi.pocketcommunity.util.UrlUtils;

import okhttp3.Callback;

public class NoticeRemoteDataSource implements NoticeDataSource {
    @Override
    public void getNotices(int pageNum, int pageSize, Callback callback) {
        StringBuilder stringBuilder = new StringBuilder().append(UrlUtils.url(UrlUtils.GET_NOTICE))
                .append("?pageNum=").append(pageNum).append("&pageSize=").append(pageSize);
        HttpUtils.get(stringBuilder.toString(), callback);
    }
}
