package com.litchi.pocketcommunity.featrue.building;

import com.litchi.pocketcommunity.data.bean.Building;

import java.util.List;

public interface BuildingContract {

    interface IBuildingView{
        void refreshList(List<Building>buildings);
        void showToast(String msg);
    }

    interface IBuildingPresenter{

        void getBuildingById(String id);
    }
}
