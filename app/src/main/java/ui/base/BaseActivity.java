package ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

abstract public class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements MvpView {

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

    protected abstract int getContentView();

    protected abstract P getActivityPresenter();

    protected abstract void initialize();
}
