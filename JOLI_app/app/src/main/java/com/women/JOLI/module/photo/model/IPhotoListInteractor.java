package com.women.JOLI.module.photo.model;

import com.women.JOLI.callback.RequestCallback;

import rx.Subscription;

public interface IPhotoListInteractor<T> {

    Subscription requestPhotoList(RequestCallback<T> callback, String id, int startPage);

}
