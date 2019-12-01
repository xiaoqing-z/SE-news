package com.women.JOLI.module.news.view;

import android.support.annotation.NonNull;

import com.women.JOLI.base.BaseView;
import com.women.JOLI.bean.NeteastNewsSummary;
import com.women.JOLI.common.DataLoadType;

import java.util.List;

public interface INewsListView extends BaseView {

    void updateNewsList(List<NeteastNewsSummary> data, @NonNull String errorMsg, @DataLoadType.DataLoadTypeChecker int type);

}
