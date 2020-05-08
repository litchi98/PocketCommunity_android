package com.litchi.pocketcommunity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.data.bean.Building;

import java.util.List;

public class ItemBuildingAdapter extends RecyclerView.Adapter<ItemBuildingAdapter.ViewHolder> {

    private List<Building> buildings;

    public ItemBuildingAdapter(List<Building> buildings) {
        this.buildings = buildings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_building, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Building building = buildings.get(position);
        holder.buildingId.setText("No."+building.getId());
        holder.buildingNumber.setText(String.valueOf(building.getBuilding()));
        holder.unit.setText(String.valueOf(building.getUnit()));
        holder.room.setText(String.valueOf(building.getRoom()));
        if (building.getUserId() == null){
            holder.owner.setText("暂未售出");
        } else {
            holder.owner.setText("业主： "+building.getUserName());
        }
    }

    @Override
    public int getItemCount() {
        return buildings.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView buildingId;
        TextView buildingNumber;
        TextView unit;
        TextView room;
        TextView owner;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            buildingId = (TextView) itemView.findViewById(R.id.item_building_No);
            buildingNumber = (TextView) itemView.findViewById(R.id.item_building_number);
            unit = (TextView) itemView.findViewById(R.id.item_building_unit);
            room = (TextView) itemView.findViewById(R.id.item_building_room);
            owner = (TextView) itemView.findViewById(R.id.item_building_owner);
        }
    }
}
