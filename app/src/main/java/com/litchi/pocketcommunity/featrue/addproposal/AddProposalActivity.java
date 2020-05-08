package com.litchi.pocketcommunity.featrue.addproposal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.base.BaseActivity;
import com.litchi.pocketcommunity.data.bean.Proposal;
import com.litchi.pocketcommunity.util.InputRemindValidator;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

public class AddProposalActivity extends BaseActivity<AddProposalPresenter> implements AddProposalContract.IAddProposalView, Validator.ValidationListener {

    private int currentUserId;
    @NotEmpty
    private EditText title;
    @NotEmpty
    private EditText content;
    private Button submit;
    private Validator validator;
    private View back;

    public static void startAction(Context context, Integer currentUserId){
        Intent intent = new Intent(context, AddProposalActivity.class);
        intent.putExtra("currentUserId", currentUserId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_proposal);
        init();
        register();
    }

    @Override
    protected void init() {
        currentUserId = getIntent().getIntExtra("currentUserId", 0);

        title = (EditText) findViewById(R.id.add_proposal_title);
        content = (EditText) findViewById(R.id.add_proposal_content);
        submit = (Button) findViewById(R.id.add_proposal_submit);
        back = findViewById(R.id.add_proposal_back);

        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    protected void register() {
        title.addTextChangedListener(new InputRemindValidator(validator, null));
        content.addTextChangedListener(new InputRemindValidator(validator, null));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telNumber = title.getText().toString();
                String password = content.getText().toString();
                Proposal proposal = new Proposal();
                proposal.setProposerId(currentUserId);
                proposal.setState(Proposal.STATE_TO_BE_PROCESSED);
                proposal.setTitle(title.getText().toString());
                proposal.setContent(content.getText().toString());
                presenter.addProposal(proposal);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected AddProposalPresenter createPresenter() {
        return new AddProposalPresenter();
    }

    @Override
    public void addSuccess() {
        Looper.prepare();
        Toast.makeText(this, "已提交成功，请等待处理", Toast.LENGTH_SHORT).show();
        finish();
        Looper.loop();
    }

    @Override
    public void onValidationSucceeded() {
        submit.setBackgroundResource(R.drawable.radius_button);
        submit.setEnabled(true);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        submit.setBackgroundResource(R.drawable.enable_radius_button);
        submit.setEnabled(false);
    }
}
