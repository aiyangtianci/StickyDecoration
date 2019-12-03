package com.example.aiyang.stickydecoration.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aiyang.stickydecoration.R;
import com.example.aiyang.stickydecoration.bean.GoodsBean;

import java.util.List;
import java.util.Locale;

public class DishAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * context
     */
    private Context mContext;
    /**
     * LayouManager
     */
    RecyclerView layout;
    /**
     * 数据
     */
    private List<GoodsBean> flist;

    public DishAdapter(Context mContext, RecyclerView layout, List<GoodsBean> flist) {
        this.mContext = mContext;
        this.layout = layout;
        this.flist = flist;
    }

    class ViewHolde extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_price;
        TextView tv_unit;
        ImageView imageView ;
        public ViewHolde(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price =itemView.findViewById(R.id.tv_price);
            tv_unit = itemView.findViewById(R.id.tv_unit);
            imageView = itemView.findViewById(R.id.iv_food);
        }
    }

    static class ViewTitle extends RecyclerView.ViewHolder {

        TextView txt;
        public ViewTitle(View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txt);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1){
            return 0;
        }else{
            return flist.get(position).getItemViewType();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        if (viewType == 1){//标题
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_layout , viewGroup ,false);
            view.setTag(true);
            return new ViewTitle(view);

        }else{//普通
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_dish,viewGroup,false);
            view.setTag(false);
            return new ViewHolde(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        GoodsBean item = flist.get(i);
        if (viewHolder instanceof ViewHolde){
            ((ViewHolde) viewHolder).tv_name.setText(item.getName());
            ((ViewHolde) viewHolder).tv_price.setText(String.format(Locale.CHINA, "￥%s", item.getPrice()));
            ((ViewHolde) viewHolder).tv_unit.setText("/" + item.getUnit());
            Glide.with(mContext).
                    load(item.getPicture_local())
                    .centerCrop()
                    .into(((ViewHolde) viewHolder).imageView);
        }else if (viewHolder instanceof ViewTitle){
            ((ViewTitle) viewHolder).txt.setTextColor(mContext.getResources().getColor(R.color.txtcolor));
            ((ViewTitle) viewHolder).txt .setBackgroundColor(mContext.getResources().getColor(R.color.whait));
            ((ViewTitle) viewHolder).txt .setTextSize(14);
            ((ViewTitle) viewHolder).txt.setText(item.getCategoryName());
            ((ViewTitle) viewHolder).txt.setTypeface(Typeface.DEFAULT_BOLD);
        }
    }

    @Override
    public int getItemCount() {
        return flist.size();
    }
}
