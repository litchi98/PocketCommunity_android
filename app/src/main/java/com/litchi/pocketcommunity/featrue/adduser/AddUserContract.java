package com.litchi.pocketcommunity.featrue.adduser;

import com.litchi.pocketcommunity.data.bean.User;

public interface AddUserContract {

    interface IAddUserView{

        void showToast(String msg);

        void addSuccess();
    }
    interface IAddUserPresenter{
        void addUser(User user);
    }
}
