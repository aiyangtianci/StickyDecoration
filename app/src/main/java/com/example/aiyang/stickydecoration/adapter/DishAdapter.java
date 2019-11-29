package com.example.aiyang.stickydecoration.adapter;

import android.content.Context;
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

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_dish,viewGroup,false);
        return new ViewHolde(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        GoodsBean item = flist.get(i);
        if (viewHolder instanceof ViewHolde){
            ((ViewHolde) viewHolder).tv_name.setText(item.getName());
            ((ViewHolde) viewHolder).tv_price.setText(String.format(Locale.CHINA, "￥%s", item.getPrice()));
            ((ViewHolde) viewHolder).tv_unit.setText("/" + item.getUnit());
            Glide.with(mContext).
                    load(item.getPicture())
                    .centerCrop()
                    .into(((ViewHolde) viewHolder).imageView);
        }
    }

    @Override
    public int getItemCount() {
        return flist.size();
    }
}
