package com.litchi.pocketcommunity.featrue.adduser;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.base.BaseActivity;
import com.litchi.pocketcommunity.data.bean.User;
import com.litchi.pocketcommunity.util.InputRemindValidator;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.util.List;

public class AddUserActivity extends BaseActivity<AddUserPresenter> implements AddUserContract.IAddUserView, Validator.ValidationListener {

    private View back;
    @NotEmpty
    private EditText name;
    @Pattern(regex = "^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$")
    private EditText telNumber;
    @Pattern(regex = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)")
    private EditText idNumber;
    private Spinner roleSpinner;

    private Integer role;

    private Validator validator;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        init();
        register();
    }

    @Override
    protected void init() {
        back = findViewById(R.id.add_user_back);
        name = (EditText) findViewById(R.id.add_user_name);
        telNumber = (EditText) findViewById(R.id.add_user_tel);
        idNumber = (EditText) findViewById(R.id.add_user_id);
        roleSpinner = (Spinner) findViewById(R.id.add_user_role);
        submit = findViewById(R.id.add_user_submit);

        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    protected void register() {
        name.addTextChangedListener(new InputRemindValidator(validator, null));
        telNumber.addTextChangedListener(new InputRemindValidator(validator, null));
        idNumber.addTextChangedListener(new InputRemindValidator(validator, null));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        roleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        role = 2;
                        break;
                    case 2:
                        role = 1;
                        break;
                    default:
                        role = null;
                        break;
                }
                validator.validate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setName(name.getText().toString());
                user.setTelNumber(telNumber.getText().toString());
                user.setIdentificationId(idNumber.getText().toString());
                user.setRoleId(role);
                presenter.addUser(user);
            }
        });
    }

    @Override
    protected AddUserPresenter createPresenter() {
        return new AddUserPresenter();
    }

    @Override
    public void onValidationSucceeded() {
        if (role != null){
            submit.setBackgroundResource(R.drawable.radius_button);
            submit.setEnabled(true);
        } else {
            submit.setBackgroundResource(R.drawable.enable_radius_button);
            submit.setEnabled(false);
        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        submit.setBackgroundResource(R.drawable.enable_radius_button);
        submit.setEnabled(false);
    }

    @Override
    public void showToast(String msg) {
        Looper.prepare();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }

    @Override
    public void addSuccess() {
        Looper.prepare();
        Toast.makeText(this, "添加成功!", Toast.LENGTH_SHORT).show();
        finish();
        Looper.loop();
    }
}
