package com.women.JOLI.module.news.model;

import com.women.JOLI.callback.RequestCallback;

import rx.Subscription;

public interface INewsListInteractor<T> {

    Subscription requestNewsList(RequestCallback<T> callback, String type, String id, int startPage);

}
