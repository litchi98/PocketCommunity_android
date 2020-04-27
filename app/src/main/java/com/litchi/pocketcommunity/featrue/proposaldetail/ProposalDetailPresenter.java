package com.litchi.pocketcommunity.featrue.proposaldetail;

import android.os.Looper;

import com.google.gson.reflect.TypeToken;
import com.litchi.pocketcommunity.base.BasePresenter;
import com.litchi.pocketcommunity.data.ProposalDataSource;
import com.litchi.pocketcommunity.data.bean.ProposalItem;
import com.litchi.pocketcommunity.data.remote.ProposalRemoteDataSource;
import com.litchi.pocketcommunity.util.JsonUtils;
import com.litchi.pocketcommunity.util.ResultMessage;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ProposalDetailPresenter extends BasePresenter<ProposalDetailActivity> implements ProposalDetailContract.IProposalDetailPresenter {

    private ProposalDataSource proposalDataSource = new ProposalRemoteDataSource();

    @Override
    public void getProposalDetail(Integer proposalId) {
        proposalDataSource.getProposalDetail(proposalId, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                ResultMessage resultMessage = JsonUtils.parseJSON(response.body().string(), ResultMessage.class);
                final List<ProposalItem> workOrderItems = JsonUtils.parseResultMessageData(resultMessage.getData("workOrderItems"),
                        new TypeToken<List<ProposalItem>>() {});
                final String name = resultMessage.getData("name").toString();
                final Integer avatarId = JsonUtils.parseResultMessageData(resultMessage.getData("avatarId"),
                        new TypeToken<Integer>() {
                        });
                getView().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getView().refreshDetail(workOrderItems);
                        getView().setNameAndAvatar(name, avatarId);
                    }
                });
            }
        });
    }

    @Override
    public void proposalTransfer(final ProposalItem proposalItem) {
        proposalDataSource.proposalTransfer(proposalItem, new Callback(){
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                ResultMessage resultMessage = JsonUtils.parseJSON(response.body().string(), ResultMessage.class);
                if (ResultMessage.SUCCESS_RESULT.equals(resultMessage.getResult())){
                    String msg = proposalItem.getTypeText() + "成功!";
                    Looper.prepare();
                    getView().showToast(msg);
                    getView().finish();
                    Looper.loop();
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }
        });
    }


}
