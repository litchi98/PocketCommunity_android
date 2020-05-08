package com.litchi.pocketcommunity.featrue.account;

import android.content.DialogInterface;

public interface AccountContract {

    interface IAccountView {
        void showToast(String msg);
        void showDialog(String title, String message, DialogInterface.OnClickListener onClickListener);
    }

     interface IAccountPresenter {
        void login(String telNumber, String password, boolean isRemember);
        void uploadImage();
    }
}
