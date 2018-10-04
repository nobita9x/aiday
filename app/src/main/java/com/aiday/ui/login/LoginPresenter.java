package com.aiday.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.aiday.data.UserItem;
import com.aiday.network.RestClient;
import com.aiday.network.body.UserBody;
import com.aiday.network.response.UserDataResponse;
import com.aiday.ui.base.BasePresenter;
import com.aiday.ui.main.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class LoginPresenter extends BasePresenter<LoginInterface.IView> implements LoginInterface.IPresenter {
    @Override
    public void loadData() {

    }

    @Override
    public void startMainScreen(Context context) {
        Intent mainIntent = new Intent(context, MainActivity.class);
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(mainIntent);
    }

}
