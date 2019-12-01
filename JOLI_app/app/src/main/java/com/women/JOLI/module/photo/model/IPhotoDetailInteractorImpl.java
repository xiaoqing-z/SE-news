package com.women.JOLI.module.photo.model;

import com.women.JOLI.base.BaseSubscriber;
import com.women.JOLI.bean.SinaPhotoDetail;
import com.women.JOLI.callback.RequestCallback;
import com.women.JOLI.http.HostType;
import com.women.JOLI.http.manager.RetrofitManager;

import rx.Subscription;

public class IPhotoDetailInteractorImpl implements IPhotoDetailInteractor<SinaPhotoDetail> {
    @Override
    public Subscription requestPhotoDetail(final RequestCallback<SinaPhotoDetail> callback, String id) {
        return RetrofitManager.getInstance(HostType.SINA_NEWS_PHOTO).getSinaPhotoDetailObservable(id)
                .subscribe(new BaseSubscriber<>(callback));
    }
}
