package com.litchi.pocketcommunity.featrue.proposal;

import com.litchi.pocketcommunity.data.bean.Proposal;

import java.util.List;

public interface ProposalContract {

    interface IProposalView{
        void showToast(String msg);
        void refreshNotDoneList(List<Proposal> notDoneList);
        void refreshDoneList(List<Proposal> doneList);
    }

    interface IProposalPresenter{
        void getDoneProposals(String condition);
        void getUndoneProposals(String condition);
    }

}
