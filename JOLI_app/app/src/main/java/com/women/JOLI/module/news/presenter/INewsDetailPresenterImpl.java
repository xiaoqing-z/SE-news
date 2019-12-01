package com.women.JOLI.module.news.presenter;

import com.women.JOLI.base.BasePresenterImpl;
import com.women.JOLI.bean.NeteastNewsDetail;
import com.women.JOLI.module.news.model.INewsDetailInteractor;
import com.women.JOLI.module.news.model.INewsDetailInteractorImpl;
import com.women.JOLI.module.news.view.INewsDetailView;

public class INewsDetailPresenterImpl extends BasePresenterImpl<INewsDetailView, NeteastNewsDetail>
        implements INewsDetailPresenter {

    public INewsDetailPresenterImpl(INewsDetailView newsDetailView, String postId) {
        super(newsDetailView);
        INewsDetailInteractor<NeteastNewsDetail> newsDetailInteractor = new INewsDetailInteractorImpl();
        mSubscription = newsDetailInteractor.requestNewsDetail(this, postId);
    }

    @Override
    public void requestSuccess(NeteastNewsDetail data) {
        mView.initNewsDetail(data);
    }

    @Override
    public void requestError(String msg) {
        super.requestError(msg);
        mView.toast(msg);
    }
}
