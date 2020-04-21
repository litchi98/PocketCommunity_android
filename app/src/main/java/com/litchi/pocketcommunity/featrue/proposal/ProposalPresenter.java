package com.litchi.pocketcommunity.featrue.proposal;

import com.google.gson.reflect.TypeToken;
import com.litchi.pocketcommunity.base.BasePresenter;
import com.litchi.pocketcommunity.data.ProposalDataSource;
import com.litchi.pocketcommunity.data.bean.Proposal;
import com.litchi.pocketcommunity.data.remote.ProposalRemoteDataSource;
import com.litchi.pocketcommunity.util.JsonUtils;
import com.litchi.pocketcommunity.util.ResultMessage;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ProposalPresenter extends BasePresenter<ProposalActivity> implements ProposalContract.IProposalPresenter {

    private ProposalDataSource proposalDataSource = new ProposalRemoteDataSource();

    @Override
    public void getDoneProposals(String condition) {
        proposalDataSource.getDoneProposal(condition, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                ResultMessage resultMessage = JsonUtils.parseJSON(response.body().string(), ResultMessage.class);
                if (resultMessage.getResult().equals(ResultMessage.ERROR_RESULT)){
                    getView().refreshNotDoneList(null);
                    return;
                }
                final List<Proposal> doneList = JsonUtils.parseResultMessageData(resultMessage.getData("doneList"),
                        new TypeToken<List<Proposal>>(){});
                getView().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getView().refreshDoneList(doneList);
                    }
                });
            }
        });
    }

    @Override
    public void getUndoneProposals(String condition) {
        proposalDataSource.getUndoneProposal(condition, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                ResultMessage resultMessage = JsonUtils.parseJSON(response.body().string(), ResultMessage.class);
                if (ResultMessage.ERROR_RESULT.equals(resultMessage.getResult())){
                    getView().refreshNotDoneList(null);
                    return;
                }
                final List<Proposal> notDoneList = JsonUtils.parseResultMessageData(resultMessage.getData("notDoneList"),
                        new TypeToken<List<Proposal>>(){});
                getView().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getView().refreshNotDoneList(notDoneList);
                    }
                });
            }
        });
    }
}