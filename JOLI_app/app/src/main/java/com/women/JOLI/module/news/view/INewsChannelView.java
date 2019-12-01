package com.women.JOLI.module.news.view;

import com.women.JOLI.base.BaseView;
import com.women.JOLI.greendao.NewsChannelTable;

import java.util.List;

public interface INewsChannelView extends BaseView {

    void initTwoRecyclerView(List<NewsChannelTable> selectChannels, List<NewsChannelTable> unSelectChannels);

}
