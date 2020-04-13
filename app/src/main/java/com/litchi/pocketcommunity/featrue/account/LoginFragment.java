package com.litchi.pocketcommunity.featrue.account;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.base.BaseFragment;
import com.litchi.pocketcommunity.util.InputRemindValidator;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.util.List;

public class LoginFragment extends BaseFragment<AccountPresenter> implements Validator.ValidationListener {

    @Pattern(regex = "^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$")
    private EditText telInput;

    @Pattern(regex = "^[a-zA-Z]\\w{5,17}$")
    private EditText pwdInput;

    private Button loginBtn;

    private Validator validator;
    private TextView pwdRemind;
    private TextView telRemind;
    private CheckBox rememberCheck;

    @Override
    protected void init(View view) {
        telInput = view.findViewById(R.id.frag_login_tel_input);
        pwdInput = view.findViewById(R.id.frag_login_pwd_input);
        loginBtn = view.findViewById(R.id.frag_login_btn);
        pwdRemind = view.findViewById(R.id.frag_login_pwd_null);
        telRemind = view.findViewById(R.id.frag_login_tel_null);
        rememberCheck = (CheckBox) view.findViewById(R.id.frag_login_remember_check);


        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    protected void registerListener() {
        telInput.addTextChangedListener(new InputRemindValidator(validator, telRemind));
        pwdInput.addTextChangedListener(new InputRemindValidator(validator, pwdRemind));

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telNumber = telInput.getText().toString();
                String password = pwdInput.getText().toString();
                boolean isRemember = rememberCheck.isChecked();
                System.out.println(presenter);
                presenter.login(telNumber, password, isRemember);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onValidationSucceeded() {
        loginBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        loginBtn.setEnabled(true);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        loginBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimaryEnable));
        loginBtn.setEnabled(false);
    }
}
