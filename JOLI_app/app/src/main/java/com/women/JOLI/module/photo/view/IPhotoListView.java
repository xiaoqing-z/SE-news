package com.women.JOLI.module.photo.view;

import android.support.annotation.NonNull;

import com.women.JOLI.base.BaseView;
import com.women.JOLI.bean.SinaPhotoList;
import com.women.JOLI.common.DataLoadType;

import java.util.List;

public interface IPhotoListView extends BaseView {

    void updatePhotoList(List<SinaPhotoList.DataEntity.PhotoListEntity> data, @NonNull String errorMsg, @DataLoadType.DataLoadTypeChecker int type);

}
