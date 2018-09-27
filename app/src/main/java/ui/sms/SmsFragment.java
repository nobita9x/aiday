package ui.sms;

import android.os.Bundle;

import aiday.com.aiday.R;
import ui.base.BaseFragment;

public class SmsFragment extends BaseFragment<SmsPresenter> implements SmsInterface.IView {

    public static SmsFragment newInstance() {
        Bundle args = new Bundle();
        SmsFragment fragment = new SmsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_sms;
    }

    @Override
    protected SmsPresenter getFragmentPresenter() {
        return new SmsPresenter();
    }
}
