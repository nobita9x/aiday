package com.aiday.network.serverApi;

import com.aiday.data.UserItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserApi {

//    @GET("/map/")
//    Observable<UserDataResponse<UserItem>> getUser(@Body UserBody body);

//    @GET("/mapList/")
//    Observable<UserDataResponse<List<UserItem>>> getUserList(@Body UserListBody body);

    @GET("/map/")
    Call<UserItem> getUser();

    @GET("/mapList/")
    Call<List<UserItem>> getUserList();
}
