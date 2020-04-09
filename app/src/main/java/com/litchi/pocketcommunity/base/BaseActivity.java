package com.litchi.pocketcommunity.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.litchi.pocketcommunity.util.MyActivityManager;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    private T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.onAttach(this);
        MyActivityManager.add(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
        MyActivityManager.remove(this);
    }

    protected abstract void init();

    protected abstract void registerListener();

    protected abstract T createPresenter();

    public T getPresenter(){
        return this.presenter;
    }
}
