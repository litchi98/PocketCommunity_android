package com.litchi.pocketcommunity.featrue.proposal;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.base.BaseActivity;
import com.litchi.pocketcommunity.data.bean.Proposal;

import java.util.ArrayList;
import java.util.List;

public class ProposalActivity extends BaseActivity<ProposalPresenter> implements ProposalContract.IProposalView {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private ArrayList<ProposalListFragment> proposalListFragments;
    private String[] tabTexts;
    private View back;
    private EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposal);
        init();
        register();
    }

    @Override
    protected void init() {
        tabLayout = (TabLayout) findViewById(R.id.proposal_tab_layout);
        viewPager2 = (ViewPager2) findViewById(R.id.proposal_view_pager);
        back = findViewById(R.id.proposal_back);
        search = (EditText) findViewById(R.id.proposal_search);

        initPageView2();
    }

    @Override
    protected void register() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    presenter.getUndoneProposals(search.getText().toString());
                    presenter.getDoneProposals(search.getText().toString());
                    hideKeyboard(search);
                }
                return false;
            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i1 > 0 && charSequence.toString().equals("")){
                    presenter.getUndoneProposals(search.getText().toString());
                    presenter.getDoneProposals(search.getText().toString());
                    System.out.println("=========");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected ProposalPresenter createPresenter() {
        return new ProposalPresenter();
    }

    private void initPageView2() {
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        proposalListFragments = new ArrayList<>();
        proposalListFragments.add(new ProposalListFragment(false));
        proposalListFragments.add(new ProposalListFragment(true));
        tabTexts = new String[]{"未关闭","已关闭"};
        viewPager2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return proposalListFragments.get(position);
            }

            @Override
            public int getItemCount() {
                return proposalListFragments.size();
            }
        });
        viewPager2.setOffscreenPageLimit(2);
        new TabLayoutMediator(tabLayout, viewPager2, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabTexts[position]);
            }
        }).attach();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void refreshNotDoneList(List<Proposal> notDoneList) {
        proposalListFragments.get(0).refreshList(notDoneList);
    }

    public void refreshDoneList(List<Proposal> doneList) {
        proposalListFragments.get(1).refreshList(doneList);
    }

    public void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
