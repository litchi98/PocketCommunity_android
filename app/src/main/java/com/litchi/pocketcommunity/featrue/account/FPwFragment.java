package com.litchi.pocketcommunity.featrue.account;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.base.BaseFragment;
import com.litchi.pocketcommunity.util.InputRemindValidator;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.util.List;

public class FPwFragment extends BaseFragment implements Validator.ValidationListener {

    @Pattern(regex = "^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$")
    private EditText telInput;

    @Pattern(regex = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)")
    private EditText idInput;

    private Validator validator;
    private Button btn;
    private View idRemind;
    private View telRemind;

    @Override
    protected void init(View view) {
        telInput = view.findViewById(R.id.frag_fpw_tel_input);
        idInput = view.findViewById(R.id.frag_fpw_id_input);
        btn = view.findViewById(R.id.frag_fpw_confirm_btn);
        idRemind = view.findViewById(R.id.frag_fpw_id_null);
        telRemind = view.findViewById(R.id.frag_fpw_tel_null);

        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    protected void registerListener() {
        telInput.addTextChangedListener(new InputRemindValidator(validator, telRemind));
        idInput.addTextChangedListener(new InputRemindValidator(validator, idRemind));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fpw;
    }

    @Override
    public void onValidationSucceeded() {
        btn.setBackgroundResource(R.drawable.radius_button);
        btn.setEnabled(true);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        btn.setBackgroundResource(R.drawable.enable_radius_button);
        btn.setEnabled(false);
    }
}
