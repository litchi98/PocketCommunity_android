package com.litchi.pocketcommunity.featrue.home;

import com.google.gson.reflect.TypeToken;
import com.litchi.pocketcommunity.base.BasePresenter;
import com.litchi.pocketcommunity.data.NoticeDataSource;
import com.litchi.pocketcommunity.data.ToolDataSource;
import com.litchi.pocketcommunity.data.bean.Notice;
import com.litchi.pocketcommunity.data.remote.NoticeRemoteDataSource;
import com.litchi.pocketcommunity.data.remote.ToolRemoteDataSource;
import com.litchi.pocketcommunity.util.JsonUtils;
import com.litchi.pocketcommunity.util.ResultMessage;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

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
    public void getNotices(int pageNum, int pageSize, final boolean isRefresh) {
        noticeDataSource.getNotices(pageNum, pageSize, new Callback(){
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                ResultMessage resultMessage = JsonUtils.parseJSON(response.body().string(), ResultMessage.class);
                Object noticesData = resultMessage.getData("notices");
                final List<Notice> notices = JsonUtils.parseResultMessageData(noticesData, new TypeToken<List<Notice>>(){});
                Object pagesData = resultMessage.getData("pages");
                final int pages = JsonUtils.parseResultMessageData(pagesData, new TypeToken<Integer>(){});
                getView().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isRefresh){
                            getView().getNoticeFragment().refreshNotices(notices);
                            getView().getNoticeFragment().setRefreshing(false);
                            getView().getNoticeFragment().setPages(pages);
                        } else {
                            getView().getNoticeFragment().updateNotices(notices);
                            getView().getNoticeFragment().setPages(pages);
                        }
                    }
                });
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
                getView().getNoticeFragment().setRefreshing(false);
            }
        });
    }
}
