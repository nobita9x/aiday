package com.aiday.ui.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

abstract public class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements MvpView {

    public static final int MY_NORMAL_PERMISSIONS_REQUEST = 1411;
    private static final int MY_OVERLAY_PERMISSIONS_REQUEST = 1412;

    protected P mActivityPresenter;
    protected AppCompatActivity mActivity;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentView());
        mUnBinder = ButterKnife.bind(this);

        mActivityPresenter = getActivityPresenter();

        mActivity = this;
        if (mActivityPresenter != null) {
            mActivityPresenter.attachView(this);
        }
        initialize();
    }

    @Override
    protected void onDestroy() {

        if (mActivityPresenter != null) {
            mActivityPresenter.detachView();
        }

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }

        super.onDestroy();
    }

    protected void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            checkOverlayPermission();

            List<String> missingPermissions = new ArrayList<>();
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(android.Manifest.permission.CALL_PHONE);
            }
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(android.Manifest.permission.READ_CALL_LOG);
            }
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(android.Manifest.permission.WRITE_CALL_LOG);
            }
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(android.Manifest.permission.READ_CONTACTS);
            }
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(android.Manifest.permission.READ_PHONE_STATE);
            }
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(android.Manifest.permission.READ_SMS);
            }
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(android.Manifest.permission.SEND_SMS);
            }

            if (!missingPermissions.isEmpty()) {
                String[] requestPermissions = new String[missingPermissions.size()];
                missingPermissions.toArray(requestPermissions);
                requestPermissions(requestPermissions, MY_NORMAL_PERMISSIONS_REQUEST);
            }
        }
    }

    private void checkOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !enableOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, MY_OVERLAY_PERMISSIONS_REQUEST);
        }
    }

    private boolean enableOverlays(Context context) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Settings.canDrawOverlays(context);
    }


    protected abstract int getContentView();

    protected abstract P getActivityPresenter();

    protected abstract void initialize();
}
