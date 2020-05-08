package com.litchi.pocketcommunity.featrue.addnotice;

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
import com.litchi.pocketcommunity.data.bean.Notice;
import com.litchi.pocketcommunity.util.InputRemindValidator;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

public class AddNoticeActivity extends BaseActivity<AddNoticePresenter> implements AddNoticeContract.IAddNoticeView, Validator.ValidationListener {

    private View back;
    @NotEmpty
    private EditText title;
    @NotEmpty
    private EditText content;
    private Button submit;
    private Validator validator;
    private int currentUserId;

    public static void startAction(Context context, Integer currentUserId){
        Intent intent = new Intent(context, AddNoticeActivity.class);
        intent.putExtra("currentUserId", currentUserId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);
        init();
        register();
    }

    @Override
    protected void init() {
        back = findViewById(R.id.add_notice_back);
        title = (EditText) findViewById(R.id.add_notice_title);
        content = (EditText) findViewById(R.id.add_notice_content);
        submit = (Button) findViewById(R.id.add_notice_submit);

        currentUserId = getIntent().getIntExtra("currentUserId", 0);

        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    protected void register() {
        title.addTextChangedListener(new InputRemindValidator(validator, null));
        content.addTextChangedListener(new InputRemindValidator(validator, null));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notice notice = new Notice();
                notice.setUserId(currentUserId);
                notice.setTitle(title.getText().toString());
                notice.setContent(content.getText().toString());
                presenter.addNotice(notice);
            }
        });
    }

    @Override
    protected AddNoticePresenter createPresenter() {
        return new AddNoticePresenter();
    }

    @Override
    public void addSuccess() {
        Looper.prepare();
        Toast.makeText(this, "已发布成功!", Toast.LENGTH_SHORT).show();
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
