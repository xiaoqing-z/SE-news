package com.women.JOLI.module.video.presenter;

import com.women.JOLI.base.BasePresenterImpl;
import com.women.JOLI.bean.NeteastVideoSummary;
import com.women.JOLI.common.DataLoadType;
import com.women.JOLI.module.video.model.IVideoListInteractor;
import com.women.JOLI.module.video.model.IVideoListInteractorImpl;
import com.women.JOLI.module.video.view.IVideoListView;

import java.util.List;

/**
 * ClassName: IVideoListPresenterImpl<p>
 * Author: oubowu<p>
 * Fuction: 视频列表代理接口实现<p>
 * CreateDate: 2016/2/23 17:09<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public class IVideoListPresenterImpl extends BasePresenterImpl<IVideoListView, List<NeteastVideoSummary>> implements IVideoListPresenter {

    private IVideoListInteractor<List<NeteastVideoSummary>> mVideoListInteractor;

    private String mId;
    private int mStartPage;

    private boolean mIsRefresh = true;
    private boolean mHasInit;

    public IVideoListPresenterImpl(IVideoListView view, String id, int startPage) {
        super(view);
        mId = id;
        mStartPage = startPage;
        mVideoListInteractor = new IVideoListInteractorImpl();
        mSubscription = mVideoListInteractor.requestVideoList(this, id, startPage);
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

        mView.updateVideoList(null, e, mIsRefresh ? DataLoadType.TYPE_REFRESH_FAIL : DataLoadType.TYPE_LOAD_MORE_FAIL);
    }


    @Override
    public void refreshData() {
        mStartPage = 0;
        mIsRefresh = true;
        mSubscription = mVideoListInteractor.requestVideoList(this, mId, mStartPage);
    }

    @Override
    public void loadMoreData() {
        mIsRefresh = false;
        mSubscription = mVideoListInteractor.requestVideoList(this, mId, mStartPage);
    }

    @Override
    public void requestSuccess(List<NeteastVideoSummary> data) {
        if (data != null && data.size() > 0) {
            mStartPage += 10;
        }
        mView.updateVideoList(data, "", mIsRefresh ? DataLoadType.TYPE_REFRESH_SUCCESS : DataLoadType.TYPE_LOAD_MORE_SUCCESS);
    }
}
