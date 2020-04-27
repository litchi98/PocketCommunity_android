package com.litchi.pocketcommunity.featrue.addproposal;

import android.os.Looper;

import com.litchi.pocketcommunity.base.BasePresenter;
import com.litchi.pocketcommunity.data.ProposalDataSource;
import com.litchi.pocketcommunity.data.bean.Proposal;
import com.litchi.pocketcommunity.data.remote.ProposalRemoteDataSource;
import com.litchi.pocketcommunity.util.JsonUtils;
import com.litchi.pocketcommunity.util.ResultMessage;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AddProposalPresenter extends BasePresenter<AddProposalActivity> implements AddProposalContract.IAddProposalPresenter {

    private ProposalDataSource proposalDataSource = new ProposalRemoteDataSource();

    @Override
    public void addProposal(Proposal proposal) {
        proposalDataSource.addProposal(proposal, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                ResultMessage resultMessage = JsonUtils.parseJSON(response.body().string(), ResultMessage.class);
                if (ResultMessage.SUCCESS_RESULT.equals(resultMessage)){
                    Looper.prepare();
                    getView().addSuccess();
                    Looper.loop();
                }
            }
        });
    }
}
