package com.litchi.pocketcommunity.data;

import okhttp3.Callback;

public interface NoticeDataSource {
    void getNotices(int pageNum, int pageSize, Callback callback);
}
