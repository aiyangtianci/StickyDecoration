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
import com.example.aiyang.stickydecoration.bean.ShopBean;

import java.util.List;
import java.util.Locale;

public class SimpleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * context
     */
    private Context mContext;
    /**
     * 数据
     */
    private List<ShopBean> flist;

    public SimpleAdapter(Context mContext, List<ShopBean> flist) {
        this.mContext = mContext;
        this.flist = flist;
    }

    class ViewHolde extends RecyclerView.ViewHolder {
        TextView shop_name;
        TextView tv_unit;
        ImageView imageView ;
        public ViewHolde(View itemView) {
            super(itemView);
            shop_name =itemView.findViewById(R.id.shop_name);
            tv_unit = itemView.findViewById(R.id.shop_description);
            imageView = itemView.findViewById(R.id.shop_img);
        }
    }

    static class ViewTitle extends RecyclerView.ViewHolder {

        TextView txt;
        public ViewTitle(View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txt);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_img,viewGroup,false);
        return new ViewHolde(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ShopBean item = flist.get(i);
        if (viewHolder instanceof ViewHolde){
            ((ViewHolde) viewHolder).shop_name.setText(item.getShopName());
            ((ViewHolde) viewHolder).tv_unit.setText(String.format(Locale.CHINA, "商家介绍：%s", item.getShopDescrition()));
            Glide.with(mContext).
                    load(item.getPicture_loacal())
                    .centerCrop()
                    .into(((ViewHolde) viewHolder).imageView);
        }
    }

    @Override
    public int getItemCount() {
        return flist.size();
    }
}
