package com.litchi.pocketcommunity.featrue.proposaldetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.adapter.ItemProposalDetailAdapter;
import com.litchi.pocketcommunity.base.BaseActivity;
import com.litchi.pocketcommunity.data.bean.Proposal;
import com.litchi.pocketcommunity.data.bean.ProposalItem;
import com.litchi.pocketcommunity.data.bean.User;
import com.litchi.pocketcommunity.util.UrlUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProposalDetailActivity extends BaseActivity<ProposalDetailPresenter>
        implements ProposalDetailContract.IProposalDetailView, View.OnClickListener {

    private CircleImageView avatar;
    private TextView name;
    private TextView date;
    private TextView title;
    private TextView content;
    private RecyclerView recyclerView;
    private View back;
    private List<ProposalItem> proposalItems = new ArrayList<>();
    private ItemProposalDetailAdapter proposalDetailAdapter;
    private Proposal proposal;
    private int roleId;
    private int currentUserId;
    private Button returnBtn;
    private Button finishBtn;
    private Button followUpBtn;
    private Button submitBtn;

    private ProposalDialog proposalDialog;

    public static void startAction(Context context, Parcelable proposal, int roleId, int currentUserId){
        Intent intent = new Intent(context, ProposalDetailActivity.class);
        intent.putExtra("proposal", proposal);
        intent.putExtra("roleId", roleId);
        intent.putExtra("currentUserId", currentUserId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposal_detail);
        init();
        register();
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        roleId = intent.getIntExtra("roleId", 2);
        currentUserId = intent.getIntExtra("currentUserId", 0);
        proposal = intent.getParcelableExtra("proposal");

        avatar = (CircleImageView) findViewById(R.id.proposal_detail_avatar);
        name = (TextView) findViewById(R.id.proposal_detail_name);
        date = (TextView) findViewById(R.id.proposal_detail_date);
        title = (TextView) findViewById(R.id.proposal_detail_title);
        content = (TextView) findViewById(R.id.proposal_detail_content);
        recyclerView = (RecyclerView) findViewById(R.id.proposal_detail_recycler);
        back = findViewById(R.id.proposal_detail_back);
        returnBtn = (Button) findViewById(R.id.proposal_detail_return);
        finishBtn = (Button) findViewById(R.id.proposal_detail_finish);
        followUpBtn = (Button) findViewById(R.id.proposal_detail_follow_up);
        submitBtn = (Button) findViewById(R.id.proposal_detail_submit);

        date.setText(new SimpleDateFormat("MM-dd HH:mm").format(proposal.getProposeDate()));
        title.setText(proposal.getTitle());
        content.setText(proposal.getContent());

        if (proposal.getState() != Proposal.STATE_FINISH
                && proposal.getCurrentProcessorId()==currentUserId && roleId == User.ROLE_STANDARD){
            returnBtn.setVisibility(View.VISIBLE);
            finishBtn.setVisibility(View.VISIBLE);
        }
        if (proposal.getState() != Proposal.STATE_FINISH
        && proposal.getCurrentProcessorId()==currentUserId && roleId == User.ROLE_MANAGER){
            followUpBtn.setVisibility(View.VISIBLE);
            submitBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void register() {
        proposalDetailAdapter = new ItemProposalDetailAdapter(proposalItems);
        recyclerView.setAdapter(proposalDetailAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter.getProposalDetail(proposal.getId());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        returnBtn.setOnClickListener(this);
        finishBtn.setOnClickListener(this);
        followUpBtn.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
    }

    @Override
    protected ProposalDetailPresenter createPresenter() {
        return new ProposalDetailPresenter();
    }

    public void refreshDetail(List<ProposalItem> proposalItems){
        if (proposalItems != null) {
            this.proposalItems.addAll(proposalItems);
            this.proposalDetailAdapter.notifyDataSetChanged();
        }
    }

    public void setNameAndAvatar(String name, Integer avatarId){
        this.name.setText(name);
        Glide.with(avatar).load(UrlUtils.url(UrlUtils.GET_IMAGE+"/"+avatarId)).into(avatar);
    }

    @Override
    public void onClick(View view) {
        ProposalItem proposalItem = new ProposalItem();
        proposalItem.setProcessorId(currentUserId);
        proposalItem.setWorkOrderId(proposal.getId());
        switch (view.getId()){
            case R.id.proposal_detail_return:
                proposalItem.setType(ProposalItem.TYPE_RETURN);
                proposalItem.setNextProcessorId(proposalItems.get(proposalItems.size()-1).getProcessorId());
                proposalItem.setNextProcessorName(proposalItems.get(proposalItems.size()-1).getProcessorName());
                this.proposalDialog = new ProposalDialog(this, proposalItem, presenter);
                proposalDialog.show();
                break;
            case R.id.proposal_detail_submit:
                proposalItem.setType(ProposalItem.TYPE_SUBMIT);
                proposalItem.setNextProcessorId(proposalItems.get(0).getProcessorId());
                proposalItem.setNextProcessorName(proposalItems.get(0).getProcessorName());
                this.proposalDialog = new ProposalDialog(this, proposalItem, presenter);
                proposalDialog.show();
                break;
            case R.id.proposal_detail_finish:
                proposalItem.setType(ProposalItem.TYPE_FINISH);
                presenter.proposalTransfer(proposalItem);
                break;
            case R.id.proposal_detail_follow_up:
                TransferListActivity.startAction(this);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (this.proposalDialog != null){
            proposalDialog.dismiss();
        }
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    int nextProcessorId = data.getIntExtra("id", 0);
                    String nextProcessorName = data.getStringExtra("name");
                    ProposalItem proposalItem = new ProposalItem();
                    proposalItem.setProcessorId(currentUserId);
                    proposalItem.setWorkOrderId(proposal.getId());
                    proposalItem.setType(ProposalItem.TYPE_FOLLOW_UP);
                    proposalItem.setNextProcessorId(nextProcessorId);
                    proposalItem.setNextProcessorName(nextProcessorName);
                    this.proposalDialog = new ProposalDialog(this, proposalItem, presenter);
                    proposalDialog.show();
                }
                break;
        }
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
