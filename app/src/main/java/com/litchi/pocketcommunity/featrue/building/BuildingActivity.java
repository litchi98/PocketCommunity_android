package com.litchi.pocketcommunity.featrue.building;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
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
import com.litchi.pocketcommunity.adapter.ItemBuildingAdapter;
import com.litchi.pocketcommunity.base.BaseActivity;
import com.litchi.pocketcommunity.data.bean.Building;

import java.util.ArrayList;
import java.util.List;

public class BuildingActivity extends BaseActivity<BuildingPresenter> implements BuildingContract.IBuildingView {

    private View back;
    private EditText search;
    private RecyclerView recyclerView;
    private FloatingActionButton actionButton;

    private List<Building> buildings = new ArrayList<>();
    private ItemBuildingAdapter buildingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);
        init();
        register();
    }

    @Override
    protected void init() {
        back = findViewById(R.id.building_back);
        search = (EditText) findViewById(R.id.building_search);
        recyclerView = (RecyclerView) findViewById(R.id.building_recycler);
        actionButton = (FloatingActionButton) findViewById(R.id.building_floatingBtn);

        buildingAdapter = new ItemBuildingAdapter(buildings);
        recyclerView.setAdapter(buildingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void register() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    getBuildingFillView();
                    hideKeyboard(search);
                }
                return false;
            }
        });
    }

    @Override
    protected BuildingPresenter createPresenter() {
        return new BuildingPresenter();
    }

    @Override
    public void showToast(String msg) {
        Looper.prepare();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }

    @Override
    public void refreshList(List<Building> buildings){
        this.buildings.clear();
        this.buildings.addAll(buildings);
        buildingAdapter.notifyDataSetChanged();
    }

    private void getBuildingFillView() {
        presenter.getBuildingById(search.getText().toString());
    }

    public void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
