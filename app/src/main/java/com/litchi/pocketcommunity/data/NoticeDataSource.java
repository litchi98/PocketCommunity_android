package com.litchi.pocketcommunity.data;

import com.litchi.pocketcommunity.data.bean.Notice;

import okhttp3.Callback;

public interface NoticeDataSource {
    void getNotices(int pageNum, int pageSize, Callback callback);

    void addNotice(Notice notice, Callback callback);
}
