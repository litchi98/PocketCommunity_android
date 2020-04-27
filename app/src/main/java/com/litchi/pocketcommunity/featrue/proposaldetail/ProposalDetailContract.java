package com.litchi.pocketcommunity.featrue.proposaldetail;

import com.litchi.pocketcommunity.data.bean.ProposalItem;

public interface ProposalDetailContract {

    interface IProposalDetailView {

    }

    interface IProposalDetailPresenter {

        void getProposalDetail(Integer proposalId);

        void proposalTransfer(ProposalItem proposalItem);

    }
}
