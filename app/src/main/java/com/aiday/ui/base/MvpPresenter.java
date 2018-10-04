package com.aiday.ui.base;

import io.reactivex.disposables.Disposable;

public interface MvpPresenter<V extends MvpView> {

    void attachView(V view);

    void detachView();

    void loadData();

}
