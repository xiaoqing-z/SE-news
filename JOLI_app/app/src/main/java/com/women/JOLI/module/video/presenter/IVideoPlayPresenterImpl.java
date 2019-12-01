package com.women.JOLI.module.video.presenter;

import com.women.JOLI.base.BasePresenterImpl;
import com.women.JOLI.module.video.view.IVideoPlayView;

public class IVideoPlayPresenterImpl extends BasePresenterImpl<IVideoPlayView, Void> implements IVideoPlayPresenter {

    public IVideoPlayPresenterImpl(IVideoPlayView view, String path, String name) {
        super(view);
        // mView.registerScreenBroadCastReceiver();
        mView.playVideo(path, name);
        mView.showProgress();
    }
}
