package com.women.JOLI.widget.slidr.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;

/**
 * Created by Oubowu on 2016/9/20 1:28.
 */
@Deprecated
public class ShadowView extends View {

    private View mCacheView;
    private LinearGradient mLinearGradient;
    private Paint mPaint;
    private RectF mRectF;
    private float mShadowOffset;
    private float mAlphaPercent = -1;
    private int[] colors;

    public ShadowView(Context context, View cacheView) {
        super(context);
        mCacheView = cacheView;
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCacheView != null && mAlphaPercent >= 0) {
            mCacheView.draw(canvas);
            // 绘制渐变阴影
            if (mLinearGradient == null) {
                mRectF = new RectF();
                colors = new int[]{Color.parseColor("#0A000000"), Color.parseColor("#66000000"), Color.parseColor("#88000000")};
                // 我设置着色器开始的位置为（getWidth() * 29 / 30，0），结束位置为（getWidth(), 0）表示我的着色器要给整个View在水平方向上渲染
                mLinearGradient = new LinearGradient(getWidth() * 29 / 30, 0, getWidth(), 0, colors, null, Shader.TileMode.REPEAT);
                mPaint.setShader(mLinearGradient);
                mRectF.set(getWidth() * 29 / 30, 0, getWidth(), getHeight());
            }
            canvas.save();
            mPaint.setAlpha((int) (mAlphaPercent * 255));
            canvas.translate(-mShadowOffset, 0);
            canvas.clipRect(mRectF);
            canvas.drawRect(mRectF, mPaint);
            canvas.restore();
        }
    }

    public void setShadowOffset(float shadowOffset, float alphaPercent) {
        mShadowOffset = shadowOffset;
        mAlphaPercent = alphaPercent;
        invalidate();
    }

}
