package com.litchi.pocketcommunity.featrue.account;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.litchi.pocketcommunity.base.BasePresenter;
import com.litchi.pocketcommunity.data.AccountDataSource;
import com.litchi.pocketcommunity.data.remote.AccountRemoteDataSource;
import com.litchi.pocketcommunity.featrue.home.HomeActivity;
import com.litchi.pocketcommunity.util.Authorization;
import com.litchi.pocketcommunity.util.JsonUtils;
import com.litchi.pocketcommunity.util.ResultMessage;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AccountPresenter extends BasePresenter<AccountActivity> implements AccountContract.IAccountPresenter {

    private AccountDataSource accountDataSource = new AccountRemoteDataSource();

    @Override
    public void login(final String telNumber, final String password, final boolean isRemember) {
        accountDataSource.login(telNumber, password, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "onFailure: call login failed...");
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String jsonStr = response.body().string();
                Log.d(TAG, "onResponse: call login success, response: " + jsonStr);
                ResultMessage resultMessage = JsonUtils.parseJSON(jsonStr, ResultMessage.class);
                if (ResultMessage.SUCCESS_RESULT.equals(resultMessage.getResult())) {
                    SharedPreferences preferences = getView().getSharedPreferences("data", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = preferences.edit();
                    if (isRemember){
                        edit.putString("telNumber", telNumber);
                        edit.putString("password", password);
                    } else {
                        edit.remove("telNumber");
                        edit.remove("password");
                    }
                    Authorization authorization = JsonUtils.parseResultMessageData(resultMessage
                            .getData("authorization"), new TypeToken<Authorization>() {});
                    edit.putLong("expireTime", authorization.getExpireTime());
                    edit.putString("token", authorization.getToken());
                    Integer avatarId = JsonUtils.parseResultMessageData(resultMessage.getData("avatarId"), new TypeToken<Integer>(){});
                    String name = resultMessage.getData("name").toString();
                    Integer roleId = JsonUtils.parseResultMessageData(resultMessage.getData("roleId"), new TypeToken<Integer>(){});
                    Integer currentUserId = JsonUtils.parseResultMessageData(resultMessage.getData("currentUserId"), new TypeToken<Integer>() {
                    });
                    edit.putString("name", name);
                    edit.apply();
                    HomeActivity.startAction(getView(), avatarId, telNumber, name, roleId, currentUserId);
                    getView().finish();
                } else {
                    getView().showToast(resultMessage.getMsg());
                }
            }
        });
    }

    @Override
    public void uploadImage() {
        
    }

}
