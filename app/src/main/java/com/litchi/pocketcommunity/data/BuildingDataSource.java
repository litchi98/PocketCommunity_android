package com.litchi.pocketcommunity.data;

import okhttp3.Callback;

public interface BuildingDataSource {
    void getBuildingById(String id, Callback callback);
}
