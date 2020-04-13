package com.litchi.pocketcommunity.featrue.home;

import com.litchi.pocketcommunity.base.BasePresenter;
import com.litchi.pocketcommunity.data.NoticeDataSource;
import com.litchi.pocketcommunity.data.ToolDataSource;
import com.litchi.pocketcommunity.data.remote.NoticeRemoteDataSource;
import com.litchi.pocketcommunity.data.remote.ToolRemoteDataSource;
import com.litchi.pocketcommunity.util.JsonUtils;
import com.litchi.pocketcommunity.util.ResultMessage;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomePresenter extends BasePresenter<HomeActivity> implements HomeContract.IHomePresenter {

    ToolDataSource toolDataSource = new ToolRemoteDataSource();
    NoticeDataSource noticeDataSource = new NoticeRemoteDataSource();

    @Override
    public void getDailyImage() {
        toolDataSource.getDailyImage(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String jsonStr = response.body().string();
                ResultMessage resultMessage = JsonUtils.parseJSON(jsonStr, ResultMessage.class);
                final String url = (String) resultMessage.getData("url");
                getView().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getView().getNoticeFragment().drawDailyImage(url);
                    }
                });
            }
        });
    }

    @Override
    public void getNotices(int pageNum, int pageSize) {
        noticeDataSource.getNotices(pageNum, pageSize, new Callback(){
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }
        });
    }
}
