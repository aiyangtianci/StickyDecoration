package com.example.aiyang.stickydecoration.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.aiyang.stickydecoration.R;
import com.example.aiyang.stickydecoration.bean.TypeBean;
import com.example.aiyang.stickydecoration.view.onItemClickListener;

import java.util.List;

public class TypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * context
     */
    private Context mContext;
    /**
     * LayouManager
     */
    RecyclerView layout;
    /**
     * 被选中的item
     */
    private int checked_item;
    /**
     * item名称
     */
    private String typeStr;

    /**
     * 数据
     */
    private List<TypeBean> data;

    /**
     * 点击
     */
    private onItemClickListener itemClickListener;

    public void addOnItemTouchListener(onItemClickListener onItemClickListener) {
        this.itemClickListener = onItemClickListener;
    }

    public TypeAdapter(Context mContext, List<TypeBean> data , RecyclerView layout) {
        this.mContext = mContext;
        this.data = data;
        this.layout = layout;
    }

    /**
     * 设置数据
     */
    public void setNewData() {
        //默认第一位选中
        if (data.size()>0){
            checked_item = 0;
            this.typeStr = data.get(checked_item).getName();
            data.get(checked_item).setChecked(true);
        }
    }


    class ViewHolde extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView item_badge;
        FrameLayout item_main;

        public ViewHolde(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            item_badge =itemView.findViewById(R.id.item_badge);
            item_main= itemView.findViewById(R.id.item_main);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //加载布局
        View item = LayoutInflater.from(mContext).inflate(R.layout.item_type , viewGroup ,false);
        return new ViewHolde(item);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        //获取一个数据
        TypeBean item = data.get(i);

        //设置列表数据
        if (viewHolder instanceof  ViewHolde){
            ((ViewHolde) viewHolder).tv_name.setText(item.getName());
            ((ViewHolde) viewHolder).item_main.setTag(item.getName());
            ((ViewHolde) viewHolder).item_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onSimpleItemClick(view,i);
                }
            });
        }

        //处理被选中的item
        if (item.isChecked()){
            ((ViewHolde) viewHolder).item_main.setBackgroundColor(Color.WHITE);
            ((ViewHolde) viewHolder).tv_name.setTextColor(Color.BLACK);
            ((ViewHolde) viewHolder).tv_name.setTypeface(Typeface.DEFAULT_BOLD);
        }else{
            ((ViewHolde) viewHolder).item_main.setBackgroundColor(0xf8f8f8);
            ((ViewHolde) viewHolder).tv_name.setTextColor(Color.parseColor("#7A7A7A"));
            ((ViewHolde) viewHolder).tv_name.setTypeface(Typeface.DEFAULT);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * 关联右侧菜品，滑动切换菜品种类
     */
    public void setTypeCheckFromScroll(String typeName) {
        //如果当前item没被选中
        if (!typeName.equals(typeStr)) {
            for (int i = 0; i < data.size(); i++) {
                //查找需要选中的item
                if (data.get(i).getName().equals(typeName) && i != checked_item) {
                    setChecked(i);
                    moveToPosition(i);
                    break;
                }
            }
        }
    }

    /**
     * 设置当前被选中的 item
     */
    public void setChecked(int checked) {
        if (this.checked_item != checked){
            //先将以选中的改为false
            data.get(checked_item).setChecked(false);
            notifyItemChanged(checked_item);

            //记录新选中设为true
            this.checked_item = checked;
            data.get(checked_item).setChecked(true);
            typeStr = data.get(checked).getName();
            notifyItemChanged(checked_item);
        }
    }

    /**
     * 移动view到指定屏幕位置
     */
    private void moveToPosition(int i) {
        LinearLayoutManager linlm = (LinearLayoutManager) layout.getLayoutManager();
        int firstItem = linlm.findFirstVisibleItemPosition();
        int lastItem = linlm.findLastVisibleItemPosition();

        if (getItemCount() > 5) {
            lastItem -= 3;//  =  last - 3
        }

        if (i <= firstItem) {
            layout.scrollToPosition(i);
        } else if (i <= lastItem) {
            //当要置顶的项已经在屏幕上显示时不处理
        } else {
            //当要置顶的项在当前显示的最后一项的后面时
            layout.scrollToPosition(i);
            int n = i - firstItem;
            if (0 <= n && n < layout.getChildCount()) {
                int top = layout.getChildAt(n).getTop();
                layout.smoothScrollBy(0, top);
            }
        }
    }
}
