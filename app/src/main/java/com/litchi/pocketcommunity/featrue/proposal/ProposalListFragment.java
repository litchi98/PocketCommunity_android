package com.litchi.pocketcommunity.featrue.proposal;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.adapter.ProposalAdapter;
import com.litchi.pocketcommunity.base.BaseFragment;
import com.litchi.pocketcommunity.data.bean.Proposal;
import com.litchi.pocketcommunity.featrue.proposaldetail.ProposalDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class ProposalListFragment extends BaseFragment<ProposalPresenter> {

    private List<Proposal> proposals = new ArrayList<>();
    private ProposalAdapter proposalAdapter;

    private boolean isDoneListFragment;

    public ProposalListFragment(boolean isDoneListFragment) {
        this.isDoneListFragment = isDoneListFragment;
    }

    @Override
    protected void init(View view) {
        final RecyclerView proposalList = (RecyclerView) view.findViewById(R.id.frag_proposal_list_recycler);
        proposalAdapter = new ProposalAdapter(proposals, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = proposalList.getChildAdapterPosition(view);
                Proposal proposal = proposals.get(position);
                if (proposal.getState() != Proposal.STATE_TO_BE_PROCESSED){
                    ProposalDetailActivity.startAction(getActivity(), proposal);
                } else {
                    Toast.makeText(getActivity(), "该工单还未处理\n请耐心等待", Toast.LENGTH_SHORT).show();
                }
            }
        });
        proposalList.setAdapter(proposalAdapter);
        proposalList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void registerListener() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isDoneListFragment){
            presenter.getDoneProposals("");
        }else {
            presenter.getUndoneProposals("");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_proposal_list;
    }

    public void refreshList(List<Proposal> proposals){
        this.proposals.clear();
        if (proposals != null){
            this.proposals.addAll(proposals);
        }
        proposalAdapter.notifyDataSetChanged();
    }
}
