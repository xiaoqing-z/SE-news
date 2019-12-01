package com.women.JOLI.module.settings.presenter;

import com.women.JOLI.base.BasePresenterImpl;
import com.women.JOLI.module.settings.view.ISettingsView;

public class ISettingsPresenterImpl extends BasePresenterImpl<ISettingsView, Void> implements ISettingsPresenter{

    public ISettingsPresenterImpl(ISettingsView view) {
        super(view);
        mView.initItemState();
    }
}
