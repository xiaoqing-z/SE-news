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
 * Created by Oubowu on 2016/9/20 0020 11:22.
 */
@Deprecated
public class LinearGradientView extends View {

    private LinearGradient mLinearGradient;
    private Paint mPaint;
    private RectF mRectF;
    private float mAlphaPercent = -1;

    public LinearGradientView(Context context) {
        super(context);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mAlphaPercent >= 0) {
            // 绘制渐变阴影
            if (mLinearGradient == null) {
                mRectF = new RectF();
                int[] colors = {Color.parseColor("#0A000000"), Color.parseColor("#66000000"), Color.parseColor("#aa000000")};
                // 我设置着色器开始的位置为（0，0），结束位置为（getWidth(), 0）表示我的着色器要给整个View在水平方向上渲染
                mLinearGradient = new LinearGradient(0, 0, getWidth(), 0, colors, null, Shader.TileMode.REPEAT);
                mPaint.setShader(mLinearGradient);
                mRectF.set(0, 0, getWidth(), getHeight());
            }
            mPaint.setAlpha((int) (mAlphaPercent * 255));
            canvas.drawRect(mRectF, mPaint);
        }
    }

    public void redraw(float alphaPercent) {
        mAlphaPercent = alphaPercent;
        invalidate();
    }


}
