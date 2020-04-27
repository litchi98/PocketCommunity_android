package com.litchi.pocketcommunity.featrue.proposaldetail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.adapter.ItemTransferListAdapter;
import com.litchi.pocketcommunity.base.BaseActivity;
import com.litchi.pocketcommunity.data.bean.User;

import java.util.ArrayList;
import java.util.List;

public class TransferListActivity extends BaseActivity<TransferListPresenter> {

    private RecyclerView recyclerView;

    List<User> users = new ArrayList<>();
    private ItemTransferListAdapter itemTransferListAdapter;

    public static void startAction(Activity activity){
        Intent intent = new Intent(activity, TransferListActivity.class);
        activity.startActivityForResult(intent, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_list);
        init();
        register();
    }

    @Override
    protected void init() {
        recyclerView = (RecyclerView) findViewById(R.id.transfer_list_recycler);
        itemTransferListAdapter = new ItemTransferListAdapter(users, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = recyclerView.getChildAdapterPosition(view);
                User user = users.get(position);
                Intent intent = new Intent();
                intent.putExtra("id", user.getId());
                intent.putExtra("name", user.getName());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        recyclerView.setAdapter(itemTransferListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter.getTransferList();
    }

    @Override
    protected void register() {

    }

    @Override
    protected TransferListPresenter createPresenter() {
        return new TransferListPresenter();
    }

    public void refreshList(List<User> users){
        this.users.addAll(users);
        itemTransferListAdapter.notifyDataSetChanged();
    }
}
