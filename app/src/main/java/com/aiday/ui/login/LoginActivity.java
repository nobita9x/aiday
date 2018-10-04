package com.aiday.ui.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aiday.R;
import com.aiday.constants.Key;
import com.aiday.preference.PrefHelper;
import com.aiday.ui.base.BaseActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import butterknife.BindView;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginInterface.IView {

    @BindView(R.id.login_button)
    LinearLayout fbLoginButton;

    private CallbackManager callbackManager;

    @Override
    protected int getContentView() {
        return R.layout.login_activity;
    }

    @Override
    protected LoginPresenter getActivityPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initialize() {

        checkPermissions();

        if (PrefHelper.getInstance().loadString(Key.TOKEN, null) != null) {
            mActivityPresenter.startMainScreen(LoginActivity.this);
        } else {
            initFacebookLogin();
        }
//        generateFBKeyHash();
    }

    private void initFacebookLogin() {
        callbackManager = CallbackManager.Factory.create();

        fbLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbLoginClick();
            }

            private void fbLoginClick() {
                final ArrayList<String> permissions = new ArrayList<>();
                permissions.add("public_profile");
                permissions.add("email");
                permissions.add("user_friends");

                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, permissions);

                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        loginSuccess(loginResult);
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(LoginActivity.this, R.string.facebookcancel, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(LoginActivity.this, R.string.login_failed, Toast.LENGTH_SHORT).show();
                    }

                    private void loginSuccess(LoginResult loginResult) {
                        PrefHelper.getInstance().saveState(Key.TOKEN, loginResult.getAccessToken().getToken());
                        PrefHelper.getInstance().saveState(Key.FACEBOOK_ID, loginResult.getAccessToken().getUserId());
                        mActivityPresenter.startMainScreen(LoginActivity.this);
                        Toast.makeText(LoginActivity.this, R.string.login_success, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        mActivityPresenter.loadData();
    }

    @SuppressLint("PackageManagerGetSignatures")
    public void generateFBKeyHash() {
        try {
            PackageInfo info = null;
            try {
                info = getPackageManager().getPackageInfo(
                        "com.aiday",
                        PackageManager.GET_SIGNATURES);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            if (info != null) {
                for (Signature signature : info.signatures) {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                }
            }
        } catch (NoSuchAlgorithmException e) {

        }
    }

}
