package ui.base;

import android.util.Log;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

abstract public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    protected V mView;
    protected CompositeDisposable mCompositeDisposable;

    @Override
    public void attachView(V mvpView) {
        this.mView = mvpView;
    }

    @Override
    public void detachView() {
        unSubscribe();
        this.mView = null;
    }

    @Override
    public void addSubscribe(Disposable subscription) {
        Log.i(this.getClass().getSimpleName(), "addSubscribe");
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    @Override
    public void unSubscribe() {
        Log.i(this.getClass().getSimpleName(), "unSubscribe - view :" + mView);
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
        //mCompositeDisposable.clear();
    }
}
