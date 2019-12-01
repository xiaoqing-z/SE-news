package com.women.JOLI.module.photo.ui.adapter;

import android.content.Context;
import android.graphics.RectF;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.women.JOLI.R;
import com.women.JOLI.bean.SinaPhotoDetail;
import com.women.JOLI.utils.GlideUtils;
import com.women.JOLI.utils.MeasureUtil;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


public class PhotoAdapter extends PagerAdapter {

    private Context mContext;
    private final int mWidth;
    private List<SinaPhotoDetail.SinaPhotoDetailPicsEntity> mPics;

    private OnPhotoExpandListener mOnPhotoExpandListener;

    public PhotoAdapter(Context context, List<SinaPhotoDetail.SinaPhotoDetailPicsEntity> pics) {
        mPics = pics == null ? new ArrayList<SinaPhotoDetail.SinaPhotoDetailPicsEntity>() : pics;
        mContext = context;
        mWidth = MeasureUtil.getScreenSize(mContext).x;
    }

    @Override
    public int getCount() {
        return mPics.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        final PhotoView photoView = new PhotoView(mContext);


        final String kpic = mPics.get(position).kpic;
        if (kpic.contains("gif")) {
            GlideUtils.loadDefault(kpic, photoView, true, null, DiskCacheStrategy.SOURCE);
            //            Glide.with(mContext).load(kpic).asGif().placeholder(R.drawable.ic_loading).animate(R.anim.image_load).error(R.drawable.ic_fail).diskCacheStrategy(DiskCacheStrategy.SOURCE)
            //                    .into(photoView);
            photoView.setZoomable(false);
        } else {
            GlideUtils.loadDefault(kpic, photoView, false, DecodeFormat.PREFER_ARGB_8888, DiskCacheStrategy.ALL);
            //            Glide.with(mContext).load(kpic).asBitmap().animate(R.anim.image_load).placeholder(R.drawable.ic_loading).error(R.drawable.ic_fail)
            //                    .format(DecodeFormat.PREFER_ARGB_8888).diskCacheStrategy(DiskCacheStrategy.ALL).into(photoView);
            photoView.setZoomable(true);
        }

        photoView.setTag(R.id.img_tag, position);

        photoView.setOnMatrixChangeListener(new PhotoViewAttacher.OnMatrixChangedListener() {
            @Override
            public void onMatrixChanged(RectF rect) {
                if (mOnPhotoExpandListener != null && rect.left < -20 && rect.right > mWidth + 20) {
                    // 显示标题
                    mOnPhotoExpandListener.onExpand(false, (int) photoView.getTag(R.id.img_tag));
                    mPics.get(position).showTitle = false;
                } else if (mOnPhotoExpandListener != null && rect.left >= -20 && rect.right <= mWidth + 20) {
                    // 隐藏标题
                    mOnPhotoExpandListener.onExpand(true, (int) photoView.getTag(R.id.img_tag));
                    mPics.get(position).showTitle = true;
                }
            }
        });

        container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void setOnPhotoExpandListener(OnPhotoExpandListener onPhotoExpandListener) {
        mOnPhotoExpandListener = onPhotoExpandListener;
    }

    /**
     * 图片被拉伸的监听接口
     */
    public interface OnPhotoExpandListener {
        void onExpand(boolean show, int position);
    }

    public List<SinaPhotoDetail.SinaPhotoDetailPicsEntity> getPics() {
        return mPics;
    }

}
