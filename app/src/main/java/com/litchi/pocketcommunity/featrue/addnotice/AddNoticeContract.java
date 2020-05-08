package com.litchi.pocketcommunity.featrue.addnotice;

import com.litchi.pocketcommunity.data.bean.Notice;

public interface AddNoticeContract {

    interface IAddNoticeView{
        void addSuccess();
    }

    interface IAddNoticePresenter{
        void addNotice(Notice notice);
    }
}
