package com.women.JOLI.module.news.presenter;

import com.women.JOLI.base.BasePresenter;


public interface INewsChannelPresenter extends BasePresenter {

    void onItemAddOrRemove(String channelName, boolean selectState);

    void onItemSwap(int fromPos, int toPos);

}
