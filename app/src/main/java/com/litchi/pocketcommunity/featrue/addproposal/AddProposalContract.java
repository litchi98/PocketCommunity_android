package com.litchi.pocketcommunity.featrue.addproposal;

import com.litchi.pocketcommunity.data.bean.Proposal;

public interface AddProposalContract {

    interface IAddProposalView{

    }

    interface IAddProposalPresenter{
        void addProposal(Proposal proposal);
    }
}
