package ui.sms;

import ui.base.MvpPresenter;
import ui.base.MvpView;

public interface SmsInterface {
    interface IView extends MvpView {

    }

    interface IPresenter extends MvpPresenter<IView> {

    }
}
