package com.litchi.pocketcommunity.data;

import okhttp3.Callback;

public interface ProposalDataSource {
    void getDoneProposal(String condition, Callback callback);

    void getUndoneProposal(String condition, Callback callback);
}
