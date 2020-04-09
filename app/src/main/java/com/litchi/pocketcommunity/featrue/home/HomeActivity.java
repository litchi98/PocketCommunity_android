package com.litchi.pocketcommunity.featrue.home;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.base.BaseActivity;
import com.litchi.pocketcommunity.base.BasePresenter;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        registerListener();
    }

    @Override
    protected void init() {

    }

    @Override
    protected void registerListener() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
