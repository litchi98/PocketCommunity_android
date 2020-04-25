package com.litchi.pocketcommunity.featrue.proposaldetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.adapter.ProposalDetailAdapter;
import com.litchi.pocketcommunity.base.BaseActivity;
import com.litchi.pocketcommunity.data.bean.Proposal;
import com.litchi.pocketcommunity.data.bean.ProposalItem;
import com.litchi.pocketcommunity.data.bean.User;
import com.litchi.pocketcommunity.util.UrlUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProposalDetailActivity extends BaseActivity<ProposalDetailPresenter> implements ProposalDetailContract.IPropoalDetailView {

    private CircleImageView avatar;
    private TextView name;
    private TextView date;
    private TextView title;
    private TextView content;
    private RecyclerView recyclerView;
    private View back;
    private List<ProposalItem> proposalItems = new ArrayList<>();
    private ProposalDetailAdapter proposalDetailAdapter;
    private Proposal proposal;
    private int roleId;
    private int currentUserId;
    private Button returnBtn;
    private Button finishBtn;
    private Button transferBtn;
    private Button commitBtn;

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
        transferBtn = (Button) findViewById(R.id.proposal_detail_transfer);
        commitBtn = (Button) findViewById(R.id.proposal_detail_commit);

        date.setText(new SimpleDateFormat("MM-dd HH:mm").format(proposal.getProposeDate()));
        title.setText(proposal.getTitle());
        content.setText(proposal.getContent());

        if (proposal.getCurrentProcessorId()==currentUserId && roleId == User.ROLE_STANDARD){
            returnBtn.setVisibility(View.VISIBLE);
            finishBtn.setVisibility(View.VISIBLE);
        }
        if (proposal.getCurrentProcessorId()==currentUserId && roleId == User.ROLE_MANAGER){
            transferBtn.setVisibility(View.VISIBLE);
            commitBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void register() {
        proposalDetailAdapter = new ProposalDetailAdapter(proposalItems);
        recyclerView.setAdapter(proposalDetailAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter.getProposalDetail(proposal.getId());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected ProposalDetailPresenter createPresenter() {
        return new ProposalDetailPresenter();
    }

    public void refreshDetail(List<ProposalItem> proposalItems){
        this.proposalItems.addAll(proposalItems);
        this.proposalDetailAdapter.notifyDataSetChanged();
    }

    public void setNameAndAvatar(String name, Integer avatarId){
        this.name.setText(name);
        Glide.with(avatar).load(UrlUtils.url(UrlUtils.GET_IMAGE+"/"+avatarId)).into(avatar);
    }
}
