package com.example.aiyang.stickydecoration.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.aiyang.stickydecoration.R;
import com.example.aiyang.stickydecoration.bean.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aiyang on 2018/4/26.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // 加载状态（默认为加载完成）
    private int loadState = 2;
    // 正在加载
    public final int LOADING = 1;
    // 加载完成
    public final int LOADING_COMPLETE = 2;
    // 加载到底
    public final int LOADING_END = 3;
    /**
     * context
     */
    public Context mContext;

    /**
     * 集合
     */
    public List<data> mDatas = new ArrayList<>();
    /**
     * data
     */
    public data mData;

    public RecyclerAdapter(Context mContext, List<data> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    static class ViewHolde extends RecyclerView.ViewHolder {

        TextView txt;
        public ViewHolde(View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txt);
        }
    }

    static class ViewHolderFoot extends RecyclerView.ViewHolder {
        TextView textfoot;
        public ViewHolderFoot(View itemView) {
            super(itemView);
            textfoot = (TextView) itemView.findViewById(R.id.list_item_foot);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 1){//标题
            View item = LayoutInflater.from(mContext).inflate(R.layout.item_layout , parent ,false);
            item.setTag(true);
            return new ViewHolde(item);
        }else if (viewType ==0){
            View item = LayoutInflater.from(mContext).inflate(R.layout.foot_layout , parent ,false);
            item.setTag(false);
            return new ViewHolderFoot(item);
        }else{
            View item = LayoutInflater.from(mContext).inflate(R.layout.item_layout , parent ,false);
            item.setTag(false);
            return new ViewHolde(item);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  ViewHolde){
            mData =mDatas.get(position);
           ((ViewHolde) holder).txt. setText(mData.getName());
           if (mData.getType() == 1){
               ((ViewHolde) holder).txt .setTextColor(mContext.getResources().getColor(R.color.colorAccent));
               ((ViewHolde) holder).txt .setBackgroundColor(mContext.getResources().getColor(R.color.backcolor));
               ((ViewHolde) holder).txt .setTextSize(20);
           }
        }else{
            switch (loadState) {
                case LOADING:
                    ((ViewHolderFoot) holder).textfoot.setText("正在加载。。");
                    ((ViewHolderFoot) holder).textfoot.setVisibility(View.VISIBLE);
                    break;
                case LOADING_COMPLETE:
                    ((ViewHolderFoot) holder).textfoot.setVisibility(View.GONE);
                    break;
                case LOADING_END:
                    ((ViewHolderFoot) holder).textfoot.setText("没有更多数据");
                    ((ViewHolderFoot) holder).textfoot.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1){
            return 0;
        }else{
            return mDatas.get(position).getType();
        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size()+1;
    }

    public void setLoadState(int loadState) {
        this.loadState = loadState;
        notifyDataSetChanged();
    }

    public boolean isLoadState() {
        return this.loadState == LOADING_COMPLETE;
    }

}
