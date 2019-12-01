package com.women.JOLI.module.news.model;

import com.women.JOLI.callback.RequestCallback;

import rx.Subscription;

public interface INewsChannelInteractor<T> {

    /**
     * 初始化查询或增删频道或排序，更新数据库
     *
     * @param callback 回调
     * @param channelName 新闻频道名称
     * @param selectState null时为初始化查询，true为选中插入数据库，false为移除出数据库
     * @return Subscription
     */
    Subscription channelDbOperate(RequestCallback<T> callback, String channelName, Boolean selectState);

    /**
     * 拖拽时候更新数据库
     *
     * @param callback 回调
     * @param fromPos 从哪个位置拖拽过来
     * @param toPos 拖拽到的位置
     * @return Subscription
     */
    Subscription channelDbSwap(RequestCallback callback, int fromPos, int toPos);
}
