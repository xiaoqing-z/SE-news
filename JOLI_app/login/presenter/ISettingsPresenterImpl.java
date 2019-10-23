package com.women.JOLI.module.login.presenter;

import com.women.JOLI.base.BasePresenterImpl;
import com.women.JOLI.module.login.view.ISettingsView;

public class ISettingsPresenterImpl extends BasePresenterImpl<ISettingsView, Void> implements ISettingsPresenter {

    public ISettingsPresenterImpl(ISettingsView view) {
        super(view);
        mView.initItemState();
    }
}
