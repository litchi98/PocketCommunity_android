package com.litchi.pocketcommunity.featrue.account;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.base.BaseFragment;
import com.litchi.pocketcommunity.util.InputRemindValidator;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.util.List;

public class RegisterFragment extends BaseFragment implements Validator.ValidationListener {

    @NotEmpty
    private EditText nameInput;

    @Pattern(regex = "^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$")
    private EditText telInput;

    @Pattern(regex = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)")
    private EditText idInput;

    private ImageView idImage;
    private ImageView contractImage;

    private View nameRemind;
    private View telRemind;
    private View idRemind;
    private View idImgRemind;
    private View contractImgRemind;
    private Validator validator;
    private Button rgtBtn;

    @Override
    protected void init(View view) {
        nameInput = view.findViewById(R.id.frag_rgt_name_input);
        telInput = view.findViewById(R.id.frag_rgt_tel_input);
        idInput = view.findViewById(R.id.frag_rgt_id_input);
        idImage = view.findViewById(R.id.frag_rgt_id_img);
        contractImage = view.findViewById(R.id.frag_rgt_contract_img);
        nameRemind = view.findViewById(R.id.frag_rgt_name_null);
        telRemind = view.findViewById(R.id.frag_rgt_tel_null);
        idRemind = view.findViewById(R.id.frag_rgt_id_null);
        idImgRemind = view.findViewById(R.id.frag_rgt_id_img_null);
        contractImgRemind = view.findViewById(R.id.frag_rgt_contract_img_null);
        rgtBtn = view.findViewById(R.id.frag_rgt_btn);

        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    protected void registerListener() {
        nameInput.addTextChangedListener(new InputRemindValidator(validator, nameRemind));
        telInput.addTextChangedListener(new InputRemindValidator(validator, telRemind));
        idInput.addTextChangedListener(new InputRemindValidator(validator, idRemind));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    public void onValidationSucceeded() {
        rgtBtn.setBackgroundResource(R.drawable.radius_button);
        rgtBtn.setEnabled(true);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        rgtBtn.setBackgroundResource(R.drawable.enable_radius_button);
        rgtBtn.setEnabled(false);
    }
}
