package com.litchi.pocketcommunity.adapter;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.data.bean.User;
import com.litchi.pocketcommunity.util.UrlUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemTransferListAdapter extends RecyclerView.Adapter<ItemTransferListAdapter.ViewHold> {

    private View.OnClickListener onClickListener;
    private List<User> users;

    public ItemTransferListAdapter(List<User> users, View.OnClickListener onClickListener) {
        this.users = users;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transfer_list, parent, false);
        view.setOnClickListener(onClickListener);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == MotionEvent.ACTION_DOWN){
                    view.setBackgroundColor(view.getResources().getColor(R.color.ActionPressed));
                } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
                    view.setBackgroundColor(view.getResources().getColor(R.color.white));
                }
                return false;
            }
        });
        ViewHold viewHold = new ViewHold(view);
        return viewHold;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHold holder, int position) {
        User user = users.get(position);
        Glide.with(holder.avatar).load(UrlUtils.url(UrlUtils.GET_IMAGE)+"/"+user.getAvatarImageId()).into(holder.avatar);
        holder.name.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ViewHold extends RecyclerView.ViewHolder {

        CircleImageView avatar;
        TextView name;

        public ViewHold(@NonNull View itemView) {
            super(itemView);
            avatar = (CircleImageView) itemView.findViewById(R.id.item_transfer_list_avatar);
            name = (TextView) itemView.findViewById(R.id.item_transfer_list_name);
        }
    }
}
