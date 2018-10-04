package com.aiday.network;

import android.content.Context;

import com.aiday.network.serverApi.UserApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.DateTime;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static Retrofit sRetrofit;
    private static Gson sGson;
    private static OkHttpClient sOkHttp;
    private static volatile RestClient sInstance;

    private static final String BASE_URL = "http://178.128.100.210:3003/";

    public static RestClient getInstance() {
        if (sInstance == null) {
            synchronized (RestClient.class) {
                if (sInstance == null) {
                    sInstance = new RestClient();
                }
            }
        }
        return sInstance;
    }

    private static Retrofit getRetrofitInstance() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gsonFactory()))
                    .build();
        }

        return sRetrofit;
    }

    private static OkHttpClient okClient() {
        if (sOkHttp == null) {
            sOkHttp = new OkHttpClient.Builder()
                    .connectTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .addInterceptor(new TokenInterceptor())
                    .addInterceptor(new LoggingInterceptor())
                    .build();
        }
        return sOkHttp;
    }

    private static Gson gsonFactory() {
        if (sGson == null) {
            sGson = new GsonBuilder()
                    .setLenient()
                    .registerTypeAdapter(DateTime.class, new DateTimeDeserializer())
                    .create();
        }
        return sGson;
    }


    public UserApi getUserApi() {
        return RestClient.getRetrofitInstance().create(UserApi.class);
    }
}
