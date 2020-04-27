package com.litchi.pocketcommunity.featrue.addproposal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.base.BaseActivity;

public class AddProposalActivity extends BaseActivity<AddProposalPresenter> {

    private int currentUserId;

    public static void startAction(Context context, Integer currentUserId){
        Intent intent = new Intent(context, AddProposalActivity.class);
        intent.putExtra("currentUserId", currentUserId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_proposal);
        init();
        register();
    }

    @Override
    protected void init() {
        currentUserId = getIntent().getIntExtra("currentUserId", 0);


    }

    @Override
    protected void register() {

    }

    @Override
    protected AddProposalPresenter createPresenter() {
        return new AddProposalPresenter();
    }

    public void addSuccess() {
        Toast.makeText(this, "已提交成功，请等待处理", Toast.LENGTH_SHORT).show();
        finish();
    }
}
