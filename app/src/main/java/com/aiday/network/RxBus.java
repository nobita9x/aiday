package com.aiday.network;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by sev_user on 16/11/2017.
 */

public class RxBus {

    private PublishSubject<Object> bus;

    private RxBus() {
        bus = PublishSubject.create();
    }

    private static class SingletonHelper {

        private static final RxBus sInstance = new RxBus();
    }

    public static RxBus getInstance() {
        return SingletonHelper.sInstance;
    }

    public void send(Object o) {
        bus.onNext(o);
    }

    public Observable<Object> toObservable() {
        return bus;
    }

}
