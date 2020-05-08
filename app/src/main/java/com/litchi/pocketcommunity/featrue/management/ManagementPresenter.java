package com.litchi.pocketcommunity.featrue.management;

import com.google.gson.reflect.TypeToken;
import com.litchi.pocketcommunity.base.BasePresenter;
import com.litchi.pocketcommunity.data.AccountDataSource;
import com.litchi.pocketcommunity.data.bean.User;
import com.litchi.pocketcommunity.data.remote.AccountRemoteDataSource;
import com.litchi.pocketcommunity.util.JsonUtils;
import com.litchi.pocketcommunity.util.ResultMessage;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ManagementPresenter extends BasePresenter<ManagementActivity> implements ManagementContract.IManagementPresenter {

    private AccountDataSource accountDataSource = new AccountRemoteDataSource();

    @Override
    public void getUserByCondition(String condition) {
        accountDataSource.getUserByCondition(condition, new Callback(){

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                ResultMessage resultMessage = JsonUtils.parseJSON(response.body().string(), ResultMessage.class);
                if (ResultMessage.SUCCESS_RESULT.equals(resultMessage.getResult())){
                    final List<User> users = JsonUtils.parseResultMessageData(resultMessage.getData("users"), new TypeToken<List<User>>() {});
                    getView().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getView().refreshList(users);
                        }
                    });
                }else {
                    getView().showToast(resultMessage.getMsg());
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }
        });
    }
}
