package com.litchi.pocketcommunity.featrue.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.adapter.ItemNoticeAdapter;
import com.litchi.pocketcommunity.base.BaseFragment;
import com.litchi.pocketcommunity.data.bean.Notice;
import com.litchi.pocketcommunity.featrue.noticedetail.NoticeDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class NoticeFragment extends BaseFragment<HomePresenter> {

    private ImageView dailyImage;
    private RecyclerView noticeRecycler;
    private SwipeRefreshLayout refreshLayout;

    private LinearLayoutManager linearLayoutManager;
    private ItemNoticeAdapter noticeAdapter;
    private List<Notice> notices = new ArrayList<>();

    private int pageNum = 1;
    private int pageSize = 4;
    private int pages = 1;

    private boolean isLoading = false;

    @Override
    protected void init(View view) {
        dailyImage = (ImageView) view.findViewById(R.id.frag_notice_dailyImage);
        noticeRecycler = (RecyclerView) view.findViewById(R.id.frag_notice_recycler_view);
        noticeAdapter = new ItemNoticeAdapter(notices, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = noticeRecycler.getChildAdapterPosition(v);
                NoticeDetailActivity.startAction(getActivity(), notices.get(position));
            }
        });
        linearLayoutManager = new LinearLayoutManager(getContext());
        noticeRecycler.setAdapter(noticeAdapter);
        noticeRecycler.setLayoutManager(linearLayoutManager);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.frag_notice_swipe_refresh);
    }

    @Override
    protected void registerListener() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNotices(1, 4, true);
            }
        });
        noticeRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {

            int lastVisibleItemPosition;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!isLoading
                        && newState==RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItemPosition+1==noticeAdapter.getItemCount()){
                    isLoading = true;
                    getNotices(pageNum, pageSize, false);
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notice;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.getDailyImage();
        getNotices(1, 4, true);
    }

    private void getNotices(int pageNum, int pageSize, boolean isRefresh){
        if (pageNum <= pages){
            presenter.getNotices(pageNum, pageSize, isRefresh);
        } else {
            presenter.getNotices(pageNum-1, pageSize, isRefresh);
        }
    }

    public void setPages(int pages){
        this.pages = pages;
    }

    public void refreshNotices(List<Notice> notices){
        if (noticeRecycler.getScrollState() == RecyclerView.SCROLL_STATE_IDLE
                && !noticeRecycler.isComputingLayout()) {
            this.notices.addAll(0, deduplication(notices, true));
            noticeAdapter.notifyDataSetChanged();
        } else {
            this.notices.addAll(0, deduplication(notices, true));
        }
    }

    public void updateNotices(List<Notice> notices){
        if (noticeRecycler.getScrollState() == RecyclerView.SCROLL_STATE_IDLE
        && !noticeRecycler.isComputingLayout()){
            this.notices.addAll(deduplication(notices, false));
            noticeAdapter.notifyDataSetChanged();
        }else{
            this.notices.addAll(deduplication(notices, false));
        }
        this.isLoading = false;
    }

    private List<Notice> deduplication(List<Notice> notices, Boolean isRefresh){
        notices.removeAll(this.notices);
        if (notices.size() > 0){
            this.pageNum += 1;
            return notices;
        } else if (!isRefresh){
            Toast.makeText(getActivity(), "没有更多了...", Toast.LENGTH_SHORT).show();
            return notices;
        }
        return notices;
    }

    public void drawDailyImage(String url) {
        Glide.with(dailyImage).load(url).into(dailyImage);
    }

    public void setRefreshing(boolean refreshing){
        refreshLayout.setRefreshing(refreshing);
    }
}
