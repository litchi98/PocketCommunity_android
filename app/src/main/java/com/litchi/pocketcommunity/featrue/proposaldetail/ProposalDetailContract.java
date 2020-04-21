package com.litchi.pocketcommunity.featrue.proposaldetail;

public interface ProposalDetailContract {

    interface IPropoalDetailView{

    }

    interface IProposalDetailPreserter{

        void getProposalDetail(Integer proposalId);
    }
}
