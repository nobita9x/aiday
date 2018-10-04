package com.aiday.ui.callLog;

import android.os.Bundle;

import com.aiday.R;
import com.aiday.ui.base.BaseFragment;

public class CallLogFragment extends BaseFragment<CallLogPresenter> implements CallLogInterface.IView {

    public static CallLogFragment newInstance() {
        Bundle args = new Bundle();
        CallLogFragment fragment = new CallLogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_call_log;
    }

    @Override
    protected CallLogPresenter getFragmentPresenter() {
        return new CallLogPresenter();
    }
}
