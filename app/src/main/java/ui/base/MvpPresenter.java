package ui.base;

import io.reactivex.disposables.Disposable;

public interface MvpPresenter<V extends MvpView> {

    void attachView(V view);

    void detachView();

    void addSubscribe(Disposable subscription);

    void unSubscribe();

    void loadData();

}
