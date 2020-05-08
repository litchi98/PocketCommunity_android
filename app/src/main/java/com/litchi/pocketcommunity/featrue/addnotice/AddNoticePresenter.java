package com.litchi.pocketcommunity.featrue.addnotice;

import com.litchi.pocketcommunity.base.BasePresenter;
import com.litchi.pocketcommunity.data.NoticeDataSource;
import com.litchi.pocketcommunity.data.bean.Notice;
import com.litchi.pocketcommunity.data.remote.NoticeRemoteDataSource;
import com.litchi.pocketcommunity.util.JsonUtils;
import com.litchi.pocketcommunity.util.ResultMessage;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AddNoticePresenter extends BasePresenter<AddNoticeActivity> implements AddNoticeContract.IAddNoticePresenter {

    private NoticeDataSource noticeDataSource = new NoticeRemoteDataSource();

    @Override
    public void addNotice(Notice notice) {
        noticeDataSource.addNotice(notice, new Callback(){
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                ResultMessage resultMessage = JsonUtils.parseJSON(response.body().string(), ResultMessage.class);
                if (ResultMessage.SUCCESS_RESULT.equals(resultMessage.getResult())){
                    getView().addSuccess();
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }
        });
    }
}
