package com.aiday.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

abstract public class BaseFragment<P extends BasePresenter> extends Fragment implements MvpView {

    // Fragment lifecycle : onAttach   >>  onCreate  >> onCreateView  >> onActivityCreated >> onStart >> onResume
    //                      onDetach() <<  onDestroy << onDestroyView <<                   << onStop  << onPause
    protected P mFragmentPresenter;
    protected View mRootView;
    private Unbinder mUnBinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragmentPresenter = getFragmentPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getFragmentView(), null);
        return mRootView;
    }

    // can insert Nested Fragment here
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mFragmentPresenter != null) {
            mFragmentPresenter.attachView(this);
        }
        mUnBinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        if (mFragmentPresenter != null) {
            mFragmentPresenter.detachView();
        }
        super.onDestroy();
    }

    protected abstract int getFragmentView();

    protected abstract P getFragmentPresenter();

}
