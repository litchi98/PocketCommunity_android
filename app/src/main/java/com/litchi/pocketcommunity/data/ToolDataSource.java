package com.litchi.pocketcommunity.data;

import okhttp3.Callback;

public interface ToolDataSource {
    void getDailyImage(Callback callback);
}
