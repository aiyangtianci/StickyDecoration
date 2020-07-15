package com.example.aiyang.stickydecoration.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.aiyang.stickydecoration.view.BaseAdapter;

import java.util.List;

/**
 * 解决双层嵌套，共用RecycleViewPool
 */
public class OutShopAdapter extends BaseAdapter<String, RecyclerView.ViewHolder> {
    RecyclerView.RecycledViewPool mSharedPool = new RecyclerView.RecycledViewPool();

    public OutShopAdapter(Context context, List mMessages) {
        super(context, mMessages);
    }

    @Override
    protected RecyclerView.ViewHolder createViewHolder(int viewType, ViewGroup parent) {
        RecyclerView childRecycleView = new RecyclerView(context);
        childRecycleView.setRecycledViewPool(mSharedPool);
        return null;
    }

    @Override
    protected void setOnBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

    }
}
