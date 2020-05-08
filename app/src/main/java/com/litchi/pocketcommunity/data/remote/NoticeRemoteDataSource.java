package com.litchi.pocketcommunity.data.remote;

import com.litchi.pocketcommunity.data.NoticeDataSource;
import com.litchi.pocketcommunity.data.bean.Notice;
import com.litchi.pocketcommunity.util.HttpUtils;
import com.litchi.pocketcommunity.util.JsonUtils;
import com.litchi.pocketcommunity.util.UrlUtils;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class NoticeRemoteDataSource implements NoticeDataSource {
    @Override
    public void getNotices(int pageNum, int pageSize, Callback callback) {
        StringBuilder stringBuilder = new StringBuilder().append(UrlUtils.url(UrlUtils.GET_NOTICE))
                .append("?pageNum=").append(pageNum).append("&pageSize=").append(pageSize);
        HttpUtils.get(stringBuilder.toString(), callback);
    }

    @Override
    public void addNotice(Notice notice, Callback callback) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtils.toJSON(notice));
        HttpUtils.post(UrlUtils.url(UrlUtils.ADD_NOTICE), requestBody, callback);
    }
}
