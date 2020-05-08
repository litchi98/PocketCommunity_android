package com.litchi.pocketcommunity.featrue.management;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.adapter.ItemManagementAdapter;
import com.litchi.pocketcommunity.base.BaseActivity;
import com.litchi.pocketcommunity.data.bean.User;
import com.litchi.pocketcommunity.featrue.adduser.AddUserActivity;

import java.util.ArrayList;
import java.util.List;

public class ManagementActivity extends BaseActivity<ManagementPresenter> implements ManagementContract.IManagementView {

    private View back;
    private RecyclerView recyclerView;
    private FloatingActionButton actionButton;

    private List<User> users = new ArrayList<>();
    private ItemManagementAdapter managementAdapter;
    private EditText search;

    private Integer currentUserId;

    public static void startAction(Context context, Integer currentUserId){
        Intent intent = new Intent(context, ManagementActivity.class);
        intent.putExtra("currentUserId", currentUserId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        init();
        register();
    }

    @Override
    protected void init() {
        search = (EditText) findViewById(R.id.management_search);
        back = findViewById(R.id.management_back);
        recyclerView = (RecyclerView) findViewById(R.id.management_recycler);
        actionButton = (FloatingActionButton) findViewById(R.id.management_floatingBtn);

        managementAdapter = new ItemManagementAdapter(users, this);
        recyclerView.setAdapter(managementAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        currentUserId = getIntent().getIntExtra("currentUserId", 0);
    }

    @Override
    protected void register() {
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    getUserFillView();
                    hideKeyboard(search);
                }
                return false;
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagementActivity.this, AddUserActivity.class);
                ManagementActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    protected ManagementPresenter createPresenter() {
        return new ManagementPresenter();
    }

    @Override
    public void refreshList(List<User> users){
        this.users.clear();
        this.users.addAll(users);
        managementAdapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void getUserFillView() {
        presenter.getUserByCondition(search.getText().toString());
    }

    public void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
