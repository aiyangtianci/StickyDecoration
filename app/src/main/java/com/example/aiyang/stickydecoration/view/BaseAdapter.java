package com.example.aiyang.stickydecoration.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseAdapter<T,K extends RecyclerView.ViewHolder>  extends RecyclerView.Adapter<K> {
    /**
     * true:滚动中，不加载，显示默认图片；
     * false：停止滚动，正常显示。默认。
     */
    protected boolean scroll;
    public List<T> mMessages;
    public Context context;
    protected final LayoutInflater layoutInflater;


    public BaseAdapter(Context context, List<T> mMessages) {
        this.mMessages = mMessages;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public K onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final K vh = createViewHolder(viewType, parent);
        return vh;
    }
    /**
     * 创建ViewHolder
     * @param viewType
     * @return
     */
    protected abstract K createViewHolder(int viewType, ViewGroup parent);

    @Override
    public void onBindViewHolder(final K viewHolder, final int position) {
        setOnBindViewHolder(viewHolder, position);
    }
    /**
     * 数据填充
     */
    protected abstract void setOnBindViewHolder(K viewHolder, int position);

    @Override
    public int getItemCount() {
        return mMessages.size();
    }
    public boolean getScrolling(){
        return scroll;
    }

    public void setScrolling(boolean scroll){
        this.scroll = scroll;
    }
}
