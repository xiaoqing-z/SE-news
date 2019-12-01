package com.women.JOLI.module.video.model;

import com.women.JOLI.callback.RequestCallback;

import rx.Subscription;
public interface IVideoListInteractor<T> {

    Subscription requestVideoList(RequestCallback<T> callback, String id, int startPage);

}
