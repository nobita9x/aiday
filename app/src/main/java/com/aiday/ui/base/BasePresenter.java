package com.aiday.ui.base;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.aiday.constants.Key;
import com.aiday.preference.PrefHelper;
import com.aiday.ui.login.LoginActivity;
import com.aiday.ui.main.MainActivity;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

abstract public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    protected V mView;

    @Override
    public void attachView(V mvpView) {
        this.mView = mvpView;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

    public void logOut(Context context){
        PrefHelper.getInstance().saveState(Key.TOKEN, null);
        PrefHelper.getInstance().saveState(Key.FACEBOOK_ID, null);

        Intent mainIntent = new Intent(context, LoginActivity.class);
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(mainIntent);
    }
}
