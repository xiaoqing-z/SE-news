package com.women.JOLI.module.news.model;

import com.women.JOLI.callback.RequestCallback;

import rx.Subscription;

public interface INewsInteractor<T> {

    Subscription operateChannelDb(RequestCallback<T> callback);

}
