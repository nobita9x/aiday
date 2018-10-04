package com.aiday.ui.main;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;

import com.aiday.constants.Key;
import com.aiday.data.UserItem;
import com.aiday.network.RestClient;
import com.aiday.preference.PrefHelper;
import com.aiday.ui.base.BasePresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter extends BasePresenter<MainInterface.IView> implements MainInterface.IPresenter {
    @Override
    public void loadData() {

    }

    @Override
    public void onDrawerItemClick(MenuItem menuItem) {

    }



    public void testAPI() {
        Call<UserItem> call = RestClient.getInstance().getUserApi().getUser();
        call.enqueue(new Callback<UserItem>() {
            @Override
            public void onResponse(Call<UserItem> call, Response<UserItem> response) {
                UserItem userItem = response.body();
                Log.d("LoginPresenter", "response = " + response.body());
            }

            @Override
            public void onFailure(Call<UserItem> call, Throwable t) {
                t.printStackTrace();
            }
        });

        Call<List<UserItem>> listCall = RestClient.getInstance().getUserApi().getUserList();
        listCall.enqueue(new Callback<List<UserItem>>() {
            @Override
            public void onResponse(Call<List<UserItem>> call, Response<List<UserItem>> response) {
                List<UserItem> userList = response.body();
                Log.d("LoginPresenter", "response = " + userList);
            }

            @Override
            public void onFailure(Call<List<UserItem>> call, Throwable t) {

            }
        });
    }


}
