package com.women.JOLI.module.video.view;

import com.women.JOLI.base.BaseView;
import com.women.JOLI.bean.NeteastVideoSummary;
import com.women.JOLI.common.DataLoadType;

import java.util.List;

public interface IVideoListView extends BaseView {

    void updateVideoList(List<NeteastVideoSummary> data, String errorMsg, @DataLoadType.DataLoadTypeChecker int type);

}
