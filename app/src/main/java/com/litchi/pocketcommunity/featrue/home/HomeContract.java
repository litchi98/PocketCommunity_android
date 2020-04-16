package com.litchi.pocketcommunity.featrue.home;

public interface HomeContract {

    interface IHomeView{

    }

    interface IHomePresenter {
        void getDailyImage();

        void getNotices(int pageNum, int pageSize, boolean isRefresh);
    }
}
