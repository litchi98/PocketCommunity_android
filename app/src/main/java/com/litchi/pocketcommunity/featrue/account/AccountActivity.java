package com.litchi.pocketcommunity.featrue.account;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class AccountActivity extends BaseActivity<AccountPresenter> implements AccountContract.IAccountView, View.OnClickListener {

    private TextView goLogin;
    private TextView goFpw;
    private List<View> gapList = new ArrayList<>();
    private LoginFragment loginFragment;
    private FPwFragment fPwFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        init();
        register();
    }

    @Override
    protected void init(){
        goLogin = (TextView) findViewById(R.id.account_go_login_frag);
        goFpw = (TextView) findViewById(R.id.account_go_fpw_frag);
        gapList.add(findViewById(R.id.go_login_gap));
        gapList.add(findViewById(R.id.go_fpw_gap));

        loginFragment = new LoginFragment();
        fPwFragment = new FPwFragment();

        changeFragment(loginFragment);
    }

    @Override
    protected void register() {
        goLogin.setOnClickListener(this);
        goFpw.setOnClickListener(this);
    }

    @Override
    protected AccountPresenter createPresenter() {
        return new AccountPresenter();
    }

    @Override
    public void showToast(String msg) {
        Looper.prepare();
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        Looper.loop();
    }

    @Override
    public void showDialog(String title, String message, DialogInterface.OnClickListener onClickListener) {
        Looper.prepare();
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", onClickListener)
                .show();
        Looper.loop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.account_go_login_frag:
                changeFragment(loginFragment);
                changeGapFocus(R.id.go_login_gap);
                break;
            case R.id.account_go_fpw_frag:
                changeFragment(fPwFragment);
                changeGapFocus(R.id.go_fpw_gap);
                break;
            default:
                break;
        }
    }

    public void changeFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.account_frag_container, fragment);
        transaction.commit();
    }

    public void changeGapFocus(int targetFocus){
        for (View view : gapList){
            if (view.getId() == targetFocus){
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.INVISIBLE);
            }
        }
    }

}
