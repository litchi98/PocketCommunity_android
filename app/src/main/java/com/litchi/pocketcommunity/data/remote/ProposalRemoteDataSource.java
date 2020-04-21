package com.litchi.pocketcommunity.data.remote;

import com.litchi.pocketcommunity.data.ProposalDataSource;
import com.litchi.pocketcommunity.util.HttpUtils;
import com.litchi.pocketcommunity.util.UrlUtils;

import okhttp3.Callback;

public class ProposalRemoteDataSource implements ProposalDataSource {
    @Override
    public void getDoneProposal(String condition, Callback callback) {
        HttpUtils.get(UrlUtils.url(UrlUtils.GET_DONE_PROPOSAL+"?condition="+condition), callback);
    }

    @Override
    public void getUndoneProposal(String condition, Callback callback) {
        HttpUtils.get(UrlUtils.url(UrlUtils.GET_UNDONE_PROPOSAL+"?condition="+condition), callback);
    }

    @Override
    public void getProposalDetail(Integer proposalId, Callback callback) {
        HttpUtils.get(UrlUtils.url(UrlUtils.GET_PROPOSAL_DETAIL+"?workOrderId="+proposalId), callback);
    }
}
