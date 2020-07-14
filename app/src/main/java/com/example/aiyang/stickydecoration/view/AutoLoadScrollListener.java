package com.example.aiyang.stickydecoration.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class AutoLoadScrollListener extends RecyclerView.OnScrollListener  {
    private boolean scrlled;
    private BaseAdapter mAdapter;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy != 0) {
            scrlled = true;
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        switch (newState) {
            case RecyclerView.SCROLL_STATE_IDLE: //（静止）
                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                if (adapter instanceof BaseAdapter) {
                    this.mAdapter = (BaseAdapter) adapter;
                }
                if (mAdapter.getScrolling() && scrlled) {
                    mAdapter.setScrolling(false);//正常显示
                    Log.d("aaa", "UI更新显示---");
                        mAdapter.notifyDataSetChanged();
                }
                scrlled = false;
                break;
            case RecyclerView.SCROLL_STATE_DRAGGING: //（上升）
                break;
            case RecyclerView.SCROLL_STATE_SETTLING: //（下落）
                break;
        }
        super.onScrollStateChanged(recyclerView, newState);
    }
}
