package com.women.JOLI.module.photo.presenter;

import com.women.JOLI.base.BasePresenterImpl;
import com.women.JOLI.module.photo.view.IPhotoView;

public class IPhotoPresenterImpl extends BasePresenterImpl<IPhotoView, Void> implements IPhotoPresenter{

    public IPhotoPresenterImpl(IPhotoView view) {
        super(view);
        view.initViewPager();
    }

}
