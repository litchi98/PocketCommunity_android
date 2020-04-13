package com.litchi.pocketcommunity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.data.bean.Notice;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {

    private List<Notice> notices;

    public NoticeAdapter(List<Notice> notices) {
        this.notices = notices;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notice, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notice notice = notices.get(position);
        holder.title.setText(notice.getTitle());
        Glide.with(holder.avatar).load(notice.getAvatarUrl()).into(holder.avatar);
        holder.name.setText(notice.getName());
        holder.content.setText(notice.getContent());
    }

    @Override
    public int getItemCount() {
        return notices.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        CircleImageView avatar;
        TextView name;
        TextView content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_notice_title);
            avatar = (CircleImageView) itemView.findViewById(R.id.item_notice_avatar);
            name = (TextView) itemView.findViewById(R.id.item_notice_name);
            content = (TextView) itemView.findViewById(R.id.item_notice_content);
        }
    }
}
