package com.litchi.pocketcommunity.featrue.home;

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.base.BaseFragment;
import com.litchi.pocketcommunity.featrue.building.BuildingActivity;
import com.litchi.pocketcommunity.featrue.management.ManagementActivity;
import com.litchi.pocketcommunity.featrue.proposal.ProposalActivity;
import com.litchi.pocketcommunity.util.UrlUtils;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommunityFragment extends BaseFragment<HomePresenter> implements View.OnClickListener, View.OnTouchListener {

    private View goPark;
    private View goBill;
    private View goProposal;

    private Integer avatarId;
    private String name;
    private String telNumber;
    private Integer roleId;
    private Integer currentUserId;
    private View goManagement;
    private View goBuilding;

    public CommunityFragment(Integer avatarId, String name, String telNumber, int roleId, int currentUserId) {
        this.avatarId = avatarId;
        this.name = name;
        this.telNumber = telNumber;
        this.roleId = roleId;
        this.currentUserId = currentUserId;
    }

    @Override
    protected void init(View view) {
        goPark = view.findViewById(R.id.frag_community_go_park);
        goBill = view.findViewById(R.id.frag_community_go_bill);
        goProposal = view.findViewById(R.id.frag_community_go_proposal);
        goManagement = view.findViewById(R.id.frag_community_go_management);
        goBuilding = view.findViewById(R.id.frag_community_go_building);

        CircleImageView avatar = (CircleImageView) view.findViewById(R.id.frag_community_avatar);
        TextView nameText = (TextView) view.findViewById(R.id.frag_community_name);
        TextView telNumberText = (TextView) view.findViewById(R.id.frag_community_telNumber);

        Glide.with(avatar).load(UrlUtils.url(UrlUtils.GET_IMAGE + "/" + avatarId)).into(avatar);
        nameText.setText(name);
        telNumber = new StringBuilder().append(telNumber.substring(0,3)).append("******")
                .append(telNumber.substring(telNumber.length()-2)).toString();
        telNumberText.setText(telNumber);
    }

    @Override
    protected void registerListener() {
        goPark.setOnClickListener(this);
        goPark.setOnTouchListener(this);
        goBill.setOnClickListener(this);
        goBill.setOnTouchListener(this);
        goProposal.setOnClickListener(this);
        goProposal.setOnTouchListener(this);
        goManagement.setOnClickListener(this);
        goManagement.setOnTouchListener(this);
        goBuilding.setOnClickListener(this);
        goBuilding.setOnTouchListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_community;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.frag_community_go_park:

                break;
            case R.id.frag_community_go_bill:

                break;
            case R.id.frag_community_go_proposal:
                Intent intent = new Intent(getActivity(), ProposalActivity.class);
                intent.putExtra("roleId", roleId);
                intent.putExtra("currentUserId", currentUserId);
                getActivity().startActivity(intent);
                break;
            case R.id.frag_community_go_management:
                ManagementActivity.startAction(getActivity(), currentUserId);
                break;
            case R.id.frag_community_go_building:
                getActivity().startActivity(new Intent(getActivity(), BuildingActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN){
            v.setBackgroundColor(getResources().getColor(R.color.ActionPressed));
        } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
            v.setBackgroundColor(getResources().getColor(R.color.white));
        }
        return false;
    }
}
