package com.women.JOLI.module.video.view;

import com.women.JOLI.base.BaseView;
import com.women.JOLI.bean.NeteastVideoSummary;
import com.women.JOLI.common.DataLoadType;

import java.util.List;

/**
 * ClassName: IVideoListView<p>
 * Author: oubowu<p>
 * Fuction: 视频列表视图接口<p>
 * CreateDate: 2016/2/23 17:05<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public interface IVideoListView extends BaseView {

    void updateVideoList(List<NeteastVideoSummary> data, String errorMsg, @DataLoadType.DataLoadTypeChecker int type);

}
