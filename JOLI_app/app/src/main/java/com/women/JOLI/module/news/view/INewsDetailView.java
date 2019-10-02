package com.women.JOLI.module.news.view;

import com.women.JOLI.base.BaseView;
import com.women.JOLI.bean.NeteastNewsDetail;

/**
 * ClassName: INewsDetailView<p>
 * Author: oubowu<p>
 * Fuction: 新闻详情视图接口<p>
 * CreateDate: 2016/2/19 14:52<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public interface INewsDetailView extends BaseView{

    void initNewsDetail(NeteastNewsDetail data);

}
