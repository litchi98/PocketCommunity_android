package com.litchi.pocketcommunity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.data.bean.ProposalItem;

import java.text.SimpleDateFormat;
import java.util.List;

public class ProposalDetailAdapter extends RecyclerView.Adapter<ProposalDetailAdapter.ViewHolder> {

    private List<ProposalItem> proposalItems;

    public ProposalDetailAdapter(List<ProposalItem> proposalItems) {
        this.proposalItems = proposalItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_proposal_detail, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProposalItem proposalItem = proposalItems.get(position);
        holder.date.setText(new SimpleDateFormat("MM-dd HH:mm").format(proposalItem.getDealDate()));
        holder.message.setText(proposalItem.getMessage());
        holder.nextProcessor.setText(proposalItem.getNextProcessorName());
        holder.processor.setText(proposalItem.getProcessorName());
        holder.type.setText(proposalItem.getTypeText());
    }

    @Override
    public int getItemCount() {
        return proposalItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView processor;
        TextView nextProcessor;
        TextView date;
        TextView message;
        TextView type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            processor = (TextView) itemView.findViewById(R.id.item_proposal_detail_processor);
            nextProcessor = (TextView) itemView.findViewById(R.id.item_proposal_detail_next_processor);
            type = (TextView) itemView.findViewById(R.id.item_proposal_detail_type);
            date = (TextView) itemView.findViewById(R.id.item_proposal_detail_date);
            message = (TextView) itemView.findViewById(R.id.item_proposal_detail_message);
        }
    }
}
