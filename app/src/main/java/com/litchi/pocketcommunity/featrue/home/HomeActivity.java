package com.litchi.pocketcommunity.featrue.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.base.BaseActivity;

public class HomeActivity extends BaseActivity<HomePresenter> implements HomeContract.IHomeView {

    private BottomNavigationView bottomNg;
    private NoticeFragment noticeFragment;
    private CommunityFragment communityFragment;
    private int roleId;

    public static void startAction(Context context, Integer avatarId, String telNumber, String name, Integer roleId){
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra("avatarId", avatarId);
        intent.putExtra("telNumber", telNumber);
        intent.putExtra("name", name);
        intent.putExtra("roleId", roleId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        registerListener();
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        int avatarId = intent.getIntExtra("avatarId", 11);
        String telNumber = intent.getStringExtra("telNumber");
        String name = intent.getStringExtra("name");
        roleId = intent.getIntExtra("roleId", 2);
        bottomNg = (BottomNavigationView) findViewById(R.id.home_bottom_navigation);
        noticeFragment = new NoticeFragment();
        communityFragment = new CommunityFragment(avatarId, name, telNumber, roleId);
        changeFragment(noticeFragment);
    }

    @Override
    protected void registerListener() {
        bottomNg.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_notice:
                        changeFragment(noticeFragment);
                        break;
                    case R.id.action_community:
                        changeFragment(communityFragment);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    private void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_frag_container, fragment);
        fragmentTransaction.commit();
    }

    public NoticeFragment getNoticeFragment(){
        return this.noticeFragment;
    }
}
