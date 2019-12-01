package com.women.JOLI.module.photo.model;

import com.women.JOLI.base.BaseSubscriber;
import com.women.JOLI.bean.SinaPhotoList;
import com.women.JOLI.callback.RequestCallback;
import com.women.JOLI.http.HostType;
import com.women.JOLI.http.manager.RetrofitManager;

import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;
import rx.functions.Func2;

public class IPhotoListInteractorImpl
        implements IPhotoListInteractor<List<SinaPhotoList.DataEntity.PhotoListEntity>> {
    @Override
    public Subscription requestPhotoList(final RequestCallback<List<SinaPhotoList.DataEntity.PhotoListEntity>> callback, String id, int startPage) {
        return RetrofitManager.getInstance(HostType.SINA_NEWS_PHOTO)
                .getSinaPhotoListObservable(id, startPage)
                .flatMap(
                        new Func1<SinaPhotoList, Observable<SinaPhotoList.DataEntity.PhotoListEntity>>() {
                            @Override
                            public Observable<SinaPhotoList.DataEntity.PhotoListEntity> call(SinaPhotoList sinaPhotoList) {
                                return Observable.from(sinaPhotoList.data.list);
                            }
                        })
                .toSortedList(
                        new Func2<SinaPhotoList.DataEntity.PhotoListEntity, SinaPhotoList.DataEntity.PhotoListEntity, Integer>() {
                            @Override
                            public Integer call(SinaPhotoList.DataEntity.PhotoListEntity photoListEntity, SinaPhotoList.DataEntity.PhotoListEntity photoListEntity2) {
                                return photoListEntity2.pubDate > photoListEntity.pubDate ? 1 : photoListEntity.pubDate == photoListEntity2.pubDate ? 0 : -1;
                            }
                        })
                .subscribe(new BaseSubscriber<>(callback));
    }

}
