package com.example.aiyang.stickydecoration.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * 自动加载数据，滚动监听
 */
public class AutoLoadScrollListener extends RecyclerView.OnScrollListener {
    private boolean scrolled;
    private BaseAdapter mAdapter;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy != 0) {
            scrolled = true;
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
                if (mAdapter.getScrolling() && scrolled) {
                    mAdapter.setScrolling(false);//正常显示
                    mAdapter.notifyDataSetChanged();
                }
                scrolled = false;
                break;
            case RecyclerView.SCROLL_STATE_DRAGGING: //（上升）
                break;
            case RecyclerView.SCROLL_STATE_SETTLING: //（下落）
                break;
        }
        super.onScrollStateChanged(recyclerView, newState);
    }

    /**
     * 改变Recycler的滑动速度
     *
     * @param recyclerView
     * @param velocity     滑动速度默认是8000dp
     */
    public static void setMaxFlingVelocity(RecyclerView recyclerView, final BaseAdapter adapter, final int velocity) {
        try {
            Field field = recyclerView.getClass().getDeclaredField("mMaxFlingVelocity");
            field.setAccessible(true);
            field.set(recyclerView, velocity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //快速滑动
        recyclerView.setOnFlingListener(new RecyclerView.OnFlingListener() {
            @Override
            public boolean onFling(int xv, int yv) {//xv是x方向滑动速度，yv是y方向滑动速度。
                Log.d("aaa", "惯性滑动---"+yv);
                if (yv >= velocity) {
                    adapter.setScrolling(true);
                }else{
                    adapter.setScrolling(false);
                }
                return false;
            }
        });
    }
}
