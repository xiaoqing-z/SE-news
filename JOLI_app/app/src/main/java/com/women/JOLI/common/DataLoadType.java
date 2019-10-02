package com.women.JOLI.common;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


public class DataLoadType {

    /**
     * 刷新成功
     */
    @DataLoadTypeChecker
    public static final int TYPE_REFRESH_SUCCESS = 1;

    /**
     * 属性失败
     */
    @DataLoadTypeChecker
    public static final int TYPE_REFRESH_FAIL = 2;

    /**
     * 加载更多成功
     */
    @DataLoadTypeChecker
    public static final int TYPE_LOAD_MORE_SUCCESS = 3;

    /**
     * 加载更多失败
     */
    @DataLoadTypeChecker
    public static final int TYPE_LOAD_MORE_FAIL = 4;

    /**
     * 替代枚举的方案，使用IntDef保证类型安全
     */
    @IntDef({TYPE_REFRESH_SUCCESS, TYPE_REFRESH_FAIL, TYPE_LOAD_MORE_SUCCESS, TYPE_LOAD_MORE_FAIL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DataLoadTypeChecker {

    }


}
