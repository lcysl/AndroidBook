package com.uestc.lcy.androidbook.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by lcy on 2018\4\25 0025.
 */

public class ArticleListRecyclerView extends RecyclerView {

    private OnLoadMoreListener mOnLoadMoreListener;

    public ArticleListRecyclerView(Context context) {
        this(context, null);
    }

    public ArticleListRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArticleListRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.mOnLoadMoreListener = onLoadMoreListener;
    }

    @Override
    public void onScrollStateChanged(int state) {
        //判断当recyclerview没有在滑动的时候是否已经滑到了最后一个item
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            LayoutManager layoutManager = getLayoutManager();

            int lastVisiblePosition;
            lastVisiblePosition= ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();

            if (layoutManager.getChildCount() > 0    //当前显示的item数量>0
                    &&lastVisiblePosition>=layoutManager.getItemCount() - 1           //当前屏幕最后一个加载项位置>=所有item的数量
                    &&layoutManager.getItemCount() > layoutManager.getChildCount()) { // 当前总Item数大于可见Item数
                if (mOnLoadMoreListener !=null){
                    mOnLoadMoreListener.onLoadMore();
                }
            }
        }
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}

