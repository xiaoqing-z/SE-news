package com.women.JOLI.module.news.presenter;

import com.women.JOLI.base.BasePresenter;

public interface INewsPresenter extends BasePresenter {

    /**
     * 频道排序或增删变化后调用此方法更新数据库
     */
    void operateChannelDb();

}
