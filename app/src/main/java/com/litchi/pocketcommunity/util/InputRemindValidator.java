package com.litchi.pocketcommunity.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.mobsandgeeks.saripaar.Validator;

public class InputRemindValidator implements TextWatcher {

    private Validator validator;
    private View remind;

    public InputRemindValidator(Validator validator, View remind){
        this.validator = validator;
        this.remind = remind;
    }

    @Override
    public void afterTextChanged(Editable s) {
        validator.validate();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!s.toString().equals("")){
            remind.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        remind.setVisibility(View.VISIBLE);
    }
}
