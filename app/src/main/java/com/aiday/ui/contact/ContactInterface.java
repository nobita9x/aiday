package com.aiday.ui.contact;

import com.aiday.ui.base.MvpPresenter;
import com.aiday.ui.base.MvpView;

public interface ContactInterface {
    interface IView extends MvpView {

    }

    interface IPresenter extends MvpPresenter<IView> {

    }
}
