package com.litchi.pocketcommunity.featrue.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.adapter.NoticeAdapter;
import com.litchi.pocketcommunity.base.BaseFragment;
import com.litchi.pocketcommunity.data.bean.Notice;

import java.util.List;

public class NoticeFragment extends BaseFragment<HomePresenter> {

    private ImageView dailyImage;
    private RecyclerView noticeRecycler;

    private List<Notice> notices;

    @Override
    protected void init(View view) {
        dailyImage = (ImageView) view.findViewById(R.id.frag_notice_dailyImage);
        noticeRecycler = (RecyclerView) view.findViewById(R.id.frag_notice_recycler_view);
    }

    @Override
    protected void registerListener() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notice;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadDailyImage();
        noticeRecycler.setAdapter(new NoticeAdapter(notices));
    }

    private void getNotices(int pageNum, int pageSize){
        presenter.getNotices(pageNum, pageSize);
    }

    public void setNotices(List<Notice> notices){
        this.notices = notices;
    }


    private void loadDailyImage() {
        presenter.getDailyImage();
    }

    public void drawDailyImage(String url) {
        Glide.with(this).load(url).into(dailyImage);
    }
}
