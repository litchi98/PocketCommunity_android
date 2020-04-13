package com.litchi.pocketcommunity.featrue.home;

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
    private MeFragment meFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        registerListener();
    }

    @Override
    protected void init() {
        bottomNg = (BottomNavigationView) findViewById(R.id.home_bottom_navigation);

        noticeFragment = new NoticeFragment();
        communityFragment = new CommunityFragment();
        meFragment = new MeFragment();

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
                    case R.id.action_me:
                        changeFragment(meFragment);
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

    public NoticeFragment getNoticeFragment() {
        return noticeFragment;
    }
}
