package com.litchi.pocketcommunity.data.remote;

import com.litchi.pocketcommunity.data.AccountDataSource;
import com.litchi.pocketcommunity.data.bean.User;
import com.litchi.pocketcommunity.util.HttpUtils;
import com.litchi.pocketcommunity.util.JsonUtils;
import com.litchi.pocketcommunity.util.UrlUtils;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;

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
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtils.toJSON(user));
        HttpUtils.post(UrlUtils.url(UrlUtils.ADD_USER), requestBody, callback);
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

    @Override
    public void getUserByRoleId(int roleId, Callback callback) {
        HttpUtils.get(UrlUtils.url(UrlUtils.GET_USERS)+"/"+roleId, callback);
    }

    @Override
    public void getUserByCondition(String condition, Callback callback) {
        HttpUtils.get(UrlUtils.url(UrlUtils.GET_USERS)+"?condition="+condition, callback);
    }
}
