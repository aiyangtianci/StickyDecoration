package com.example.aiyang.stickydecoration.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.net.InterfaceAddress;

/**
 * Created by aiyang on 2019/3/22.
 */

public  class UpPullRecyclerViewOnScrollListener extends RecyclerView.OnScrollListener {

    //监听回调
    UpPullOnScrollListener listener;

    public UpPullRecyclerViewOnScrollListener(UpPullOnScrollListener listener) {
        this.listener = listener;
    }

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
                listener.onLoadMoreData();
            }

            int fristItemPosition = manager.findFirstCompletelyVisibleItemPosition();//第一个显示的位置
            if (fristItemPosition == (0) && !isUpPull){
                listener.onRefreshData();
            }
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        // 大于0表示正在向上滑动，小于等于0表示停止或向下滑动
        isUpPull = dy > 0;
    }



}


