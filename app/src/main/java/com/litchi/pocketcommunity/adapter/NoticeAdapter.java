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
import com.litchi.pocketcommunity.util.UrlUtils;

import java.text.SimpleDateFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {

    private List<Notice> notices;

    private View.OnClickListener onClickListener;

    private static final int NORMAL_VIEW = 0;
    private static final int FOOT_VIEW = 1;

    public NoticeAdapter(List<Notice> notices, View.OnClickListener onClickListener) {
        this.notices = notices;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == NORMAL_VIEW){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notice, parent, false);
            view.setOnClickListener(onClickListener);
        } else if (viewType == FOOT_VIEW){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notice_footview, parent, false);
        }
        return new ViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (getItemViewType(position) == NORMAL_VIEW){
            Notice notice = notices.get(position);
            holder.title.setText(notice.getTitle());
            Glide.with(holder.avatar).load(UrlUtils.url(UrlUtils.GET_IMAGE + "/" + notice.getAvatarId())).into(holder.avatar);
            holder.name.setText(notice.getName());
            holder.content.setText(notice.getContent());
            holder.publishDate.setText(new SimpleDateFormat("MM-dd HH:mm").format(notice.getPublishDate()));
        }
    }

    @Override
    public int getItemCount() {
        return notices.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1){
            return FOOT_VIEW;
        }
        return NORMAL_VIEW;
    }



    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        CircleImageView avatar;
        TextView name;
        TextView content;
        TextView publishDate;
        View footView;

        public ViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            if (viewType == NORMAL_VIEW){
                title = (TextView) itemView.findViewById(R.id.item_notice_title);
                avatar = (CircleImageView) itemView.findViewById(R.id.item_notice_avatar);
                name = (TextView) itemView.findViewById(R.id.item_notice_name);
                content = (TextView) itemView.findViewById(R.id.item_notice_content);
                publishDate = (TextView) itemView.findViewById(R.id.item_notice_publish_date);
            } else if (viewType == FOOT_VIEW) {
                footView = itemView;
            }
        }
    }
}
