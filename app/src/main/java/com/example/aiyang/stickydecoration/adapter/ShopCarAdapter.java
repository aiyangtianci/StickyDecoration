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
import com.example.aiyang.stickydecoration.bean.ShopBean;

import java.util.List;
import java.util.Locale;

public class ShopCarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * context
     */
    private Context mContext;
    /**
     * 数据
     */
    private List<GoodsBean> flist;

    public ShopCarAdapter(Context mContext, List<GoodsBean> flist) {
        this.mContext = mContext;
        this.flist = flist;
    }

    class ViewHolde extends RecyclerView.ViewHolder {
        TextView car_name;
        TextView car_specification;
        ImageView imageView ;
        TextView car_price;
        public ViewHolde(View itemView) {
            super(itemView);
            car_name =itemView.findViewById(R.id.car_name);
            car_specification = itemView.findViewById(R.id.car_specification);
            car_price = itemView.findViewById(R.id.car_price);
            imageView = itemView.findViewById(R.id.car_img);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_car,viewGroup,false);
        return new ViewHolde(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        GoodsBean item = flist.get(i);
        if (viewHolder instanceof ViewHolde){
            ((ViewHolde) viewHolder).car_name.setText(item.getName());
            ((ViewHolde) viewHolder).car_specification.setText(String.format(Locale.CHINA, "商家介绍：%s", item.getDescription()));
            ((ViewHolde) viewHolder).car_price.setText(String.format(Locale.CHINA,"¥ %s",item.getPrice()));
            Glide.with(mContext).
                    load(item.getPicture_local())
                    .centerCrop()
                    .into(((ViewHolde) viewHolder).imageView);
        }
    }

    @Override
    public int getItemCount() {
        return flist.size();
    }
}
