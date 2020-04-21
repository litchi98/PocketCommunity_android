package com.litchi.pocketcommunity.featrue.proposaldetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.base.BaseActivity;

public class ProposalDetailActivity extends BaseActivity<ProposalDetailPresenter> implements ProposalDetailContract.IPropoalDetailView {

    public static void startAction(Context context, Parcelable proposal){
        Intent intent = new Intent(context, ProposalDetailActivity.class);
        intent.putExtra("proposal", proposal);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposal_detail);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void register() {

    }

    @Override
    protected ProposalDetailPresenter createPresenter() {
        return new ProposalDetailPresenter();
    }
}
