package com.aiday.ui.login;

import android.app.Activity;
import android.content.Context;

import com.aiday.ui.base.MvpPresenter;
import com.aiday.ui.base.MvpView;

public interface LoginInterface {
    interface IView extends MvpView {

    }

    interface IPresenter extends MvpPresenter<IView> {

        void startMainScreen(Context context);
    }
}
