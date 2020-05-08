package com.litchi.pocketcommunity.data.remote;

import com.litchi.pocketcommunity.data.BuildingDataSource;
import com.litchi.pocketcommunity.util.HttpUtils;
import com.litchi.pocketcommunity.util.UrlUtils;

import okhttp3.Callback;

public class BuildingRemoteDataSource implements BuildingDataSource {
    @Override
    public void getBuildingById(String id, Callback callback) {
        HttpUtils.get(UrlUtils.url(UrlUtils.GET_BUILDING)+ "/"+id, callback);
    }
}
