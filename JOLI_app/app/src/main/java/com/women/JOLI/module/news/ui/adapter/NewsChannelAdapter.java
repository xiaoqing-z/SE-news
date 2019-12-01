package com.women.JOLI.module.news.ui.adapter;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.women.JOLI.R;
import com.women.JOLI.base.BaseRecyclerViewHolder;
import com.women.JOLI.base.BaseRecyclerAdapter;
import com.women.JOLI.callback.SimpleItemTouchHelperCallback;
import com.women.JOLI.greendao.NewsChannelTable;
import com.socks.library.KLog;

import java.util.Collections;
import java.util.List;

public class NewsChannelAdapter extends BaseRecyclerAdapter<NewsChannelTable>
        implements SimpleItemTouchHelperCallback.OnMoveAndSwipeListener {

    private SimpleItemTouchHelperCallback mItemTouchHelperCallback;

    private OnItemMoveListener mItemMoveListener;

    public NewsChannelAdapter(Context context, List<NewsChannelTable> data) {
        super(context, data);
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.item_news_channel;
    }

    @Override
    public NewsChannelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_MORE) {
            return new NewsChannelHolder(mContext,
                    mInflater.inflate(R.layout.item_load_more, parent, false));
        } else {
            final NewsChannelHolder holder = new NewsChannelHolder(mContext,
                    mInflater.inflate(getItemLayoutId(viewType), parent, false));
            if (mClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClickListener.onItemClick(v, holder.getLayoutPosition());
                    }

                });
            }
            if (mItemTouchHelperCallback != null) {
                holder.itemView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        // 触摸事件发生的时候，如果是定死频道，直接不给拖拽
                        if (mData.get(holder.getLayoutPosition()).getNewsChannelFixed()) {
                            KLog.e("触摸事件发生的时候，如果是定死频道，直接不给拖拽");
                            mItemTouchHelperCallback.setLongPressDragEnabled(false);
                            return true;
                        } else {
                            mItemTouchHelperCallback.setLongPressDragEnabled(true);
                        }

                        return false;
                    }
                });
            }
            return holder;
        }
    }

    @Override
    public void bindData(final BaseRecyclerViewHolder holder, int position, NewsChannelTable item) {
        holder.getTextView(R.id.tv_news_channel).setText(item.getNewsChannelName());
        holder.getTextView(R.id.tv_news_channel).setSelected(item.getNewsChannelFixed());
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        // 前三固定死不能交换
        if (!mData.get(fromPosition).getNewsChannelFixed() && !mData.get(toPosition)
                .getNewsChannelFixed()) {
            //交换mItems数据的位置
            Collections.swap(mData, fromPosition, toPosition);
            //交换RecyclerView列表中item的位置
            notifyItemMoved(fromPosition, toPosition);

            if (mItemMoveListener != null) {
                mItemMoveListener.onItemMove(fromPosition, toPosition);
            }

            return true;
        }
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        //删除mItems数据
        mData.remove(position);
        //删除RecyclerView列表对应item
        notifyItemRemoved(position);
    }

    public void setItemTouchHelper(SimpleItemTouchHelperCallback callback1) {
        mItemTouchHelperCallback = callback1;
    }

    /**
     * 实现长按选中的效果
     */
    private class NewsChannelHolder extends BaseRecyclerViewHolder
            implements SimpleItemTouchHelperCallback.OnStateChangedListener {

        public NewsChannelHolder(Context context, View itemView) {
            super(context, itemView);
        }

        @Override
        public void onItemSelected() {
            // Enable设为false，改变背景颜色
            itemView.setEnabled(false);
        }

        @Override
        public void onItemClear() {
            // Enable设为true，恢复背景颜色
            itemView.setEnabled(true);
        }
    }

    /**
     * 设置item拖拽移动的监听
     * @param itemMoveListener item移动时的监听
     */
    public void setItemMoveListener(OnItemMoveListener itemMoveListener) {
        mItemMoveListener = itemMoveListener;
    }

    public interface OnItemMoveListener {
        void onItemMove(int fromPosition, int toPosition);
    }


}
