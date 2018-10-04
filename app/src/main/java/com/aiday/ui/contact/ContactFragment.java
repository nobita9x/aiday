package com.aiday.ui.contact;

import android.os.Bundle;

import com.aiday.R;
import com.aiday.ui.base.BaseFragment;

public class ContactFragment extends BaseFragment<ContactPresenter> implements ContactInterface.IView {

    public static ContactFragment newInstance() {
        Bundle args = new Bundle();
        ContactFragment fragment = new ContactFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_sms;
    }

    @Override
    protected ContactPresenter getFragmentPresenter() {
        return new ContactPresenter();
    }
}
