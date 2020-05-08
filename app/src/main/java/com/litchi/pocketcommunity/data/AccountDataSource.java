package com.litchi.pocketcommunity.data;

import com.litchi.pocketcommunity.data.bean.User;

import okhttp3.Callback;

public interface AccountDataSource {
    void register(User user, Callback callback);
    void login(String telNumber, String password, Callback callback);
    void saveUser(User user, Callback callback);
    void deleteUser(int id, Callback callback);
    void updateUser(User user, Callback callback);
    void getUser(int id, Callback callback);

    void getUserByRoleId(int roleId, Callback callback);

    void getUserByCondition(String condition, Callback callback);
}
