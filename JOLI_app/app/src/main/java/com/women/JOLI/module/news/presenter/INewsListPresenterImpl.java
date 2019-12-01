package com.women.JOLI.module.news.presenter;

import com.women.JOLI.base.BasePresenterImpl;
import com.women.JOLI.bean.NeteastNewsSummary;
import com.women.JOLI.common.DataLoadType;
import com.women.JOLI.module.news.model.INewsListInteractor;
import com.women.JOLI.module.news.model.INewsListInteractorImpl;
import com.women.JOLI.module.news.view.INewsListView;

import java.util.List;

public class INewsListPresenterImpl extends BasePresenterImpl<INewsListView, List<NeteastNewsSummary>> implements INewsListPresenter {

    private INewsListInteractor<List<NeteastNewsSummary>> mNewsListInteractor;
    private String mNewsType;
    private String mNewsId;
    private int mStartPage;

    private boolean mIsRefresh = true;
    private boolean mHasInit;

    public INewsListPresenterImpl(INewsListView newsListView, String newsId, String newsType) {
        super(newsListView);
        mNewsListInteractor = new INewsListInteractorImpl();
        mSubscription = mNewsListInteractor.requestNewsList(this, newsType, newsId, mStartPage);
        mNewsType = newsType;
        mNewsId = newsId;
    }

    @Override
    public void beforeRequest() {
        if (!mHasInit) {
            mHasInit = true;
            mView.showProgress();
        }
    }

    @Override
    public void requestError(String e) {
        super.requestError(e);
        mView.updateNewsList(null, e, mIsRefresh ? DataLoadType.TYPE_REFRESH_FAIL : DataLoadType.TYPE_LOAD_MORE_FAIL);
    }

    @Override
    public void requestSuccess(List<NeteastNewsSummary> data) {
        if (data != null) {
            mStartPage += 20;
        }
        mView.updateNewsList(data, "", mIsRefresh ? DataLoadType.TYPE_REFRESH_SUCCESS : DataLoadType.TYPE_LOAD_MORE_SUCCESS);

    }

    @Override
    public void refreshData() {
        mStartPage = 0;
        mIsRefresh = true;
        mSubscription = mNewsListInteractor.requestNewsList(this, mNewsType, mNewsId, mStartPage);
    }

    @Override
    public void loadMoreData() {
        mIsRefresh = false;
        mSubscription = mNewsListInteractor.requestNewsList(this, mNewsType, mNewsId, mStartPage);
    }

}
