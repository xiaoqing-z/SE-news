package com.women.JOLI.module.news.view;

import com.women.JOLI.base.BaseView;
import com.women.JOLI.greendao.NewsChannelTable;

import java.util.List;

public interface INewsView extends BaseView {

    void initViewPager(List<NewsChannelTable> newsChannels);

    void initRxBusEvent();

}
