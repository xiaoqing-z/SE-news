package com.women.JOLI.module.photo.presenter;

import com.women.JOLI.base.BasePresenterImpl;
import com.women.JOLI.bean.SinaPhotoDetail;
import com.women.JOLI.module.photo.model.IPhotoDetailInteractor;
import com.women.JOLI.module.photo.model.IPhotoDetailInteractorImpl;
import com.women.JOLI.module.photo.view.IPhotoDetailView;

public class IPhotoDetailPresenterImpl extends BasePresenterImpl<IPhotoDetailView, SinaPhotoDetail>
        implements IPhotoDetailPresenter {

    private IPhotoDetailInteractor<SinaPhotoDetail> mDetailInteractor;

    public IPhotoDetailPresenterImpl(IPhotoDetailView view, String id, SinaPhotoDetail data) {
        super(view);
        mDetailInteractor = new IPhotoDetailInteractorImpl();
        if (data != null) {
            mView.initViewPager(data);
        } else {
            mSubscription = mDetailInteractor.requestPhotoDetail(this, id);
        }
    }

    @Override
    public void requestSuccess(SinaPhotoDetail data) {
        mView.initViewPager(data);
    }

    @Override
    public void requestError(String msg) {
        super.requestError(msg);
        mView.toast(msg);
    }
}
