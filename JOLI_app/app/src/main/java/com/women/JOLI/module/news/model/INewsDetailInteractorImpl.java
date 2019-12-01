package com.women.JOLI.module.news.model;

import com.women.JOLI.base.BaseSubscriber;
import com.women.JOLI.bean.NeteastNewsDetail;
import com.women.JOLI.callback.RequestCallback;
import com.women.JOLI.http.HostType;
import com.women.JOLI.http.manager.RetrofitManager;

import java.util.Map;

import rx.Subscription;
import rx.functions.Func1;

public class INewsDetailInteractorImpl implements INewsDetailInteractor<NeteastNewsDetail> {

    @Override
    public Subscription requestNewsDetail(final RequestCallback<NeteastNewsDetail> callback, final String id) {
        return RetrofitManager.getInstance(HostType.NETEASE_NEWS_VIDEO).getNewsDetailObservable(id)
                .map(new Func1<Map<String, NeteastNewsDetail>, NeteastNewsDetail>() {
                    @Override
                    public NeteastNewsDetail call(Map<String, NeteastNewsDetail> map) {
                        return map.get(id);
                    }
                }).subscribe(new BaseSubscriber<>(callback));
    }

}
