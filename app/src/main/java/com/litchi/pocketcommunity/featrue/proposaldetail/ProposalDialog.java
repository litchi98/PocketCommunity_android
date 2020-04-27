package com.litchi.pocketcommunity.featrue.proposaldetail;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.data.bean.ProposalItem;

public class ProposalDialog extends Dialog {

    private EditText message;
    private TextView title;
    private Button positive;
    private Button negative;

    private ProposalItem proposalItem;

    private ProposalDetailPresenter presenter;

    public ProposalDialog(@NonNull Context context, ProposalItem proposalItem, ProposalDetailPresenter presenter) {
        super(context, R.style.CustomDialog);
        this.proposalItem = proposalItem;
        this.presenter = presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_proposal_detail);
        setCanceledOnTouchOutside(false);
        init();
        register();
    }

    private void register() {
        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proposalItem.setMessage(getMessage());
                presenter.proposalTransfer(proposalItem);
            }
        });
        negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private void init() {
        message = (EditText) findViewById(R.id.dialog_proposal_detail_message);
        title = (TextView) findViewById(R.id.dialog_proposal_detail_title);
        positive = (Button) findViewById(R.id.dialog_proposal_detail_positive);
        negative = (Button) findViewById(R.id.dialog_proposal_detail_negative);

        setDialogTitle();
    }

    private void setDialogTitle() {
        Integer type = proposalItem.getType();
        String nextProcessorName = proposalItem.getNextProcessorName();
        switch (type){
            case ProposalItem.TYPE_RETURN:
                this.title.setText("退回给： " + nextProcessorName);
                break;
            case ProposalItem.TYPE_SUBMIT:
                this.title.setText("提交给： " + nextProcessorName);
                break;
            case ProposalItem.TYPE_FOLLOW_UP:
                this.title.setText("转派给： " + nextProcessorName);
            default:
                break;
        }
    }

    public String getMessage(){
        return message.getText().toString();
    }
}
