package com.women.JOLI.module.photo.model;

import com.women.JOLI.callback.RequestCallback;

import rx.Subscription;

public interface IPhotoDetailInteractor<T> {

    Subscription requestPhotoDetail(RequestCallback<T> callback, String id);

}
