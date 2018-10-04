package com.aiday;

import android.app.Application;

import com.aiday.preference.PrefHelper;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class AidayApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PrefHelper.setContext(getApplicationContext());
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(AidayApplication.this);

    }
}
