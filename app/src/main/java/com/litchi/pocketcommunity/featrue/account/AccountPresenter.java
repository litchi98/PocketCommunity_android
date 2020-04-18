package com.litchi.pocketcommunity.featrue.account;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.litchi.pocketcommunity.R;
import com.litchi.pocketcommunity.base.BasePresenter;
import com.litchi.pocketcommunity.data.AccountDataSource;
import com.litchi.pocketcommunity.data.bean.User;
import com.litchi.pocketcommunity.data.remote.AccountRemoteDataSource;
import com.litchi.pocketcommunity.featrue.home.HomeActivity;
import com.litchi.pocketcommunity.util.JsonUtils;
import com.litchi.pocketcommunity.util.ResultMessage;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.litchi.pocketcommunity.util.Constant.LOGIN_VERIFY_REFUSAL;

public class AccountPresenter extends BasePresenter<AccountActivity> implements AccountContract.IAccountPresenter {

    private AccountDataSource accountDataSource = new AccountRemoteDataSource();

    @Override
    public void login(final String telNumber, final String password, final boolean isRemember) {
        accountDataSource.login(telNumber, password, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "onFailure: call login failed...");
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String jsonStr = response.body().string();
                Log.d(TAG, "onResponse: call login success, response: " + jsonStr);
                ResultMessage resultMessage = JsonUtils.parseJSON(jsonStr, ResultMessage.class);
                if (ResultMessage.SUCCESS_RESULT.equals(resultMessage.getResult())) {
                    SharedPreferences preferences = getView().getSharedPreferences("data", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = preferences.edit();
                    if (isRemember){
                        edit.putString("telNumber", telNumber);
                        edit.putString("password", password);
                    } else {
                        edit.remove("telNumber");
                        edit.remove("password");
                    }
                    LinkedTreeMap authorization = (LinkedTreeMap)resultMessage.getData("authorization");
                    edit.putString("expireTime", authorization.get("expireTime").toString());
                    edit.putString("token", authorization.get("token").toString());
                    Integer avatarId = JsonUtils.parseResultMessageData(resultMessage.getData("avatarId"), new TypeToken<Integer>(){});
                    String name = resultMessage.getData("name").toString();
                    Integer roleId = JsonUtils.parseResultMessageData(resultMessage.getData("roleId"), new TypeToken<Integer>(){});
                    edit.putString("name", name);
                    edit.apply();
                    HomeActivity.startAction(getView(), avatarId, telNumber, name, roleId);
                } else {
                    if (resultMessage.getMsg().equals(LOGIN_VERIFY_REFUSAL)){
                        getView().showDialog(resultMessage.getMsg(), " result: " + resultMessage.getData("reason")
                                + "\nplease register again...", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getView().changeFragment(new RegisterFragment());
                                getView().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        getView().changeGapFocus(R.id.go_register_gap);
                                    }
                                });
                            }
                        });
                    }
                    getView().showToast(resultMessage.getMsg());
                }
            }
        });
    }

    @Override
    public void register(String name, String password, String gender, String identificationId, int identificationImageId, int contractImageId) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setGender(gender);
        user.setIdentificationId(identificationId);
        user.setContractImageId(contractImageId);
        accountDataSource.register(user, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "onFailure: call register failed...");
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                ResultMessage resultMessage = new Gson().fromJson(response.body().toString(), ResultMessage.class);
                if (ResultMessage.SUCCESS_RESULT.equals(resultMessage.getResult())){
                    getView().changeFragment(new LoginFragment());
                } else {
                    ((AccountContract.IAccountView)getView()).showToast(resultMessage.getMsg());
                }
            }
        });
    }

    @Override
    public void uploadImage() {
        
    }

}
