package com.example.aiyang.stickydecoration.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by aiyang on 2019/3/22.
 */

public abstract class UpPullRecyclerViewOnScrollListener extends RecyclerView.OnScrollListener {

    /**
     * 标记是否正在向上滑动
     */
    boolean isUpPull = false;
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();

        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            int itemCount = manager.getItemCount();//总数
            int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();//最后显示的位置

            if (lastItemPosition == (itemCount - 1) && isUpPull) {
                onLoadMoreData();
            }
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        // 大于0表示正在向上滑动，小于等于0表示停止或向下滑动
        isUpPull = dy > 0;
    }

    /**
     * 加载更多数据的方法
     */
    public abstract  void onLoadMoreData() ;
}
