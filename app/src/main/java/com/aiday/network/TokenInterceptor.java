package com.aiday.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        String accessToken = "FPJjIzzY1j4VbMY4FOV4iSW1PwaHiXZPIuSFKGpQLeEqO/aICNvp2gDyraSkfxCUyeGOvt5DLfRIE8Gs9Hxj8E2B5JvuoJKgd43R7ZwppO3f5Si44+2h7oe1009R7oG/OLDirREbvIZeuXSKaLm2b6ywOLzhaBWKvfbj0SyoPLs=";
        Request.Builder requestBuilder = original.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("token", accessToken)
                .header("email", "dongocthien@gmail.com")
                .header("q", "84985934902")
                .method(original.method(), original.body());
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}