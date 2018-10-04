package com.aiday.ui.main;

import android.content.Context;
import android.view.MenuItem;

import com.aiday.ui.base.MvpPresenter;
import com.aiday.ui.base.MvpView;

public interface MainInterface {
    interface IView extends MvpView {

    }

    interface IPresenter extends MvpPresenter<IView> {

        void onDrawerItemClick(MenuItem menuItem);
    }
}
