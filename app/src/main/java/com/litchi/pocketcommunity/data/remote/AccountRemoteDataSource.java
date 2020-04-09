package com.litchi.pocketcommunity.data.remote;

import android.util.Log;

import com.litchi.pocketcommunity.data.AccountDataSource;
import com.litchi.pocketcommunity.data.bean.User;
import com.litchi.pocketcommunity.util.HttpUtils;
import com.litchi.pocketcommunity.util.JsonUtils;
import com.litchi.pocketcommunity.util.UrlUtils;

import okhttp3.Callback;
import okhttp3.FormBody;

public class AccountRemoteDataSource implements AccountDataSource {

    @Override
    public void register(User user, Callback callback) {
        FormBody formBody = new FormBody.Builder()
                .add("user", JsonUtils.toJSON(user))
                .build();
        HttpUtils.post(UrlUtils.url(UrlUtils.REGISTER), formBody, callback);
    }

    @Override
    public void login(String telNumber, String password, Callback callback) {
        FormBody formBody = new FormBody.Builder()
                .add("telNumber", telNumber)
                .add("password", password)
                .build();
        String url = UrlUtils.url(UrlUtils.ACCOUNT_LOGIN);
        HttpUtils.post(url, formBody, callback);
    }

    @Override
    public void saveUser(User user, Callback callback) {

    }

    @Override
    public void deleteUser(int id, Callback callback) {
    }

    @Override
    public void updateUser(User user, Callback callback) {
    }

    @Override
    public void getUser(int id, Callback callback) {
    }
}
