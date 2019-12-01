package com.women.JOLI.module.video.presenter;

import com.women.JOLI.base.BasePresenterImpl;
import com.women.JOLI.module.video.view.IVideoView;

public class IVideoPresenterImpl extends BasePresenterImpl<IVideoView, Void>
        implements IVideoPresenter {

    public IVideoPresenterImpl(IVideoView view) {
        super(view);
        mView.initViewPager();
    }


}
