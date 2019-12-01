package com.women.JOLI.module.news.model;

import com.women.JOLI.callback.RequestCallback;

import rx.Subscription;

public interface INewsDetailInteractor<T> {

    Subscription requestNewsDetail(RequestCallback<T> callback, String id);

}
