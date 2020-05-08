package com.litchi.pocketcommunity.featrue.adduser;

import com.litchi.pocketcommunity.base.BasePresenter;
import com.litchi.pocketcommunity.data.AccountDataSource;
import com.litchi.pocketcommunity.data.bean.User;
import com.litchi.pocketcommunity.data.remote.AccountRemoteDataSource;
import com.litchi.pocketcommunity.util.JsonUtils;
import com.litchi.pocketcommunity.util.ResultMessage;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AddUserPresenter extends BasePresenter<AddUserActivity> implements AddUserContract.IAddUserPresenter {

    private AccountDataSource accountDataSource = new AccountRemoteDataSource();

    @Override
    public void addUser(User user) {
        accountDataSource.saveUser(user, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                ResultMessage resultMessage = JsonUtils.parseJSON(response.body().string(), ResultMessage.class);
                if (ResultMessage.SUCCESS_RESULT.equals(resultMessage.getResult())){
                    getView().addSuccess();
                } else {
                    getView().showToast(resultMessage.getMsg());
                }
            }
        });
    }
}
