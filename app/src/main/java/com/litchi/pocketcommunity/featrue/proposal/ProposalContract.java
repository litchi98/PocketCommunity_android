package com.litchi.pocketcommunity.featrue.proposal;

public interface ProposalContract {

    interface IProposalView{
        void showToast(String msg);
    }

    interface IProposalPresenter{
        void getDoneProposals(String condition);
        void getUndoneProposals(String condition);
    }

}
