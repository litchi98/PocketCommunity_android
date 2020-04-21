package com.litchi.pocketcommunity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.data.bean.Proposal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProposalAdapter extends RecyclerView.Adapter<ProposalAdapter.ViewHolder> {

    private List<Proposal> proposals;

    public ProposalAdapter(List<Proposal> proposals) {
        this.proposals = proposals;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_proposal, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Proposal proposal = proposals.get(position);
        holder.title.setText(proposal.getTitle());
        holder.content.setText(proposal.getContent());
        Date proposeDate = proposal.getProposeDate();
        holder.date.setText(new SimpleDateFormat("MM-dd HH:mm").format(proposeDate));
        Integer proposalState = proposal.getState();
        holder.state.setText(proposal.getStateText());
        if (proposalState == Proposal.STATE_TO_BE_CONFIRMED){
            holder.state.setBackgroundResource(R.drawable.radius_state_text_confirm);
        } else if (proposalState != Proposal.STATE_FINISH && (new Date().getTime() - proposeDate.getTime()) > 604800000L){
            holder.state.setBackgroundResource(R.drawable.radius_state_text_high);
        } else if(proposalState != Proposal.STATE_FINISH) {
            holder.state.setBackgroundResource(R.drawable.radius_state_text_low);
        }
    }

    @Override
    public int getItemCount() {
        return proposals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView content;
        TextView date;
        TextView state;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_proposal_title);
            content = (TextView) itemView.findViewById(R.id.item_proposal_content);
            date = (TextView) itemView.findViewById(R.id.item_proposal_date);
            state = (TextView) itemView.findViewById(R.id.item_proposal_state);
        }
    }
}
