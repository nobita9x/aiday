package ui.main;

import ui.base.MvpPresenter;
import ui.base.MvpView;

public interface MainInterface {
    interface IView extends MvpView {

    }

    interface IPresenter extends MvpPresenter<IView> {

    }
}
