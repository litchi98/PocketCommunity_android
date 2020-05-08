package com.litchi.pocketcommunity.featrue.building;

import com.google.gson.reflect.TypeToken;
import com.litchi.pocketcommunity.base.BasePresenter;
import com.litchi.pocketcommunity.data.BuildingDataSource;
import com.litchi.pocketcommunity.data.bean.Building;
import com.litchi.pocketcommunity.data.remote.BuildingRemoteDataSource;
import com.litchi.pocketcommunity.util.JsonUtils;
import com.litchi.pocketcommunity.util.ResultMessage;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BuildingPresenter extends BasePresenter<BuildingActivity> implements BuildingContract.IBuildingPresenter {

    private BuildingDataSource buildingDataSource = new BuildingRemoteDataSource();

    @Override
    public void getBuildingById(String id) {
        buildingDataSource.getBuildingById(id, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                ResultMessage resultMessage = JsonUtils.parseJSON(response.body().string(), ResultMessage.class);
                if (ResultMessage.SUCCESS_RESULT.equals(resultMessage.getResult())){
                    final List<Building> buildings = JsonUtils.parseResultMessageData(resultMessage.getData("buildings"), new TypeToken<List<Building>>() {});
                    getView().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getView().refreshList(buildings);
                        }
                    });
                } else {
                    getView().showToast(resultMessage.getMsg());
                }
            }
        });
    }
}
