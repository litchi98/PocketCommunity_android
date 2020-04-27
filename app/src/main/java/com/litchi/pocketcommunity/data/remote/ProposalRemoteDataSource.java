package com.litchi.pocketcommunity.data.remote;

import com.litchi.pocketcommunity.data.ProposalDataSource;
import com.litchi.pocketcommunity.data.bean.Proposal;
import com.litchi.pocketcommunity.data.bean.ProposalItem;
import com.litchi.pocketcommunity.util.HttpUtils;
import com.litchi.pocketcommunity.util.JsonUtils;
import com.litchi.pocketcommunity.util.UrlUtils;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.RequestBody;

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

    @Override
    public void proposalTransfer(ProposalItem proposalItem, Callback callback) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtils.toJSON(proposalItem));
        HttpUtils.post(UrlUtils.url(UrlUtils.PROPOSAL_TRANSFER), requestBody, callback);
    }

    @Override
    public void addProposal(Proposal proposal, Callback callback) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtils.toJSON(proposal));
        HttpUtils.post(UrlUtils.url(UrlUtils.ADD_PROPOSAL), requestBody, callback);
    }
}
