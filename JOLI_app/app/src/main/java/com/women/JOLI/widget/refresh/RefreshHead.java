package com.women.JOLI.widget.refresh;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

public abstract class RefreshHead extends View {

    public RefreshHead(Context context) {
        super(context);
    }

    public RefreshHead(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshHead(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RefreshHead(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 头部正在刷新
     *
     * @return
     */
    public abstract boolean isLoading();

    /**
     * 头部准备好刷新
     *
     * @return
     */
    public abstract boolean isReadyLoad();

    /**
     * 执行加载完的效果，把加载动画移除
     */
    public abstract void performLoaded();

    /**
     * 执行加载动画
     */
    public abstract void performLoading();

    /**
     * 响应拉动时的操作
     *
     * @param v
     */
    public abstract void performPull(float v);
}
