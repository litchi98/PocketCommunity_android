package com.litchi.pocketcommunity.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.data.bean.User;
import com.litchi.pocketcommunity.featrue.management.ManagementActivity;
import com.litchi.pocketcommunity.util.UrlUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemManagementAdapter extends RecyclerView.Adapter<ItemManagementAdapter.ViewHolder> {

    private final ManagementActivity activity;
    private List<User> users;

    public ItemManagementAdapter(List<User> users, ManagementActivity activity) {
        this.users = users;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final User user = users.get(position);
        Glide.with(holder.avatar).load(UrlUtils.url(UrlUtils.GET_IMAGE+"/"+user.getAvatarImageId())).into(holder.avatar);
        holder.name.setText(user.getName());
        String telNumber = user.getTelNumber();
        telNumber = new StringBuilder().append(telNumber.substring(0,3)).append("******")
                .append(telNumber.substring(telNumber.length()-2)).toString();
        holder.tel.setText(telNumber);
        String identificationId = user.getIdentificationId();
        String str = identificationId.substring(6,14);
        identificationId = identificationId.replace(str, "*******");
        holder.id.setText(identificationId);
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:" + user.getTelNumber());
                activity.startActivity(new Intent(Intent.ACTION_DIAL, uri));
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView avatar;
        TextView name;
        TextView tel;
        TextView id;
        View call;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = (CircleImageView) itemView.findViewById(R.id.item_user_avatar);
            name = (TextView) itemView.findViewById(R.id.item_user_name);
            tel = (TextView) itemView.findViewById(R.id.item_user_tel);
            id = (TextView) itemView.findViewById(R.id.item_user_id);
            call = itemView.findViewById(R.id.item_user_call);
        }
    }
}
