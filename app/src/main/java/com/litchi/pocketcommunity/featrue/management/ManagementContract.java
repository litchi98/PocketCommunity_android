package com.litchi.pocketcommunity.featrue.management;

import com.litchi.pocketcommunity.data.bean.User;

import java.util.List;

public interface ManagementContract {

    interface IManagementView{
        void refreshList(List<User> users);
        void showToast(String msg);
    }

    interface IManagementPresenter{
        void getUserByCondition(String condition);
    }
}
