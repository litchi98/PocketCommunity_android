package com.litchi.pocketcommunity.data;

import com.litchi.pocketcommunity.data.bean.Proposal;
import com.litchi.pocketcommunity.data.bean.ProposalItem;

import okhttp3.Callback;

public interface ProposalDataSource {
    void getDoneProposal(String condition, Callback callback);

    void getUndoneProposal(String condition, Callback callback);

    void getProposalDetail(Integer proposalId, Callback callback);

    void proposalTransfer(ProposalItem proposalItem, Callback callback);

    void addProposal(Proposal proposal, Callback callback);
}
