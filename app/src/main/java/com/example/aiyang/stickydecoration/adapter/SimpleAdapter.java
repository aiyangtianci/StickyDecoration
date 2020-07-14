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
import com.example.aiyang.stickydecoration.view.BaseAdapter;

import java.util.List;
import java.util.Locale;

/**
 * 餐厅适配器
 */
public class SimpleAdapter extends BaseAdapter<ShopBean,RecyclerView.ViewHolder> {

    /**
     * context
     */
    private Context mContext;
    /**
     * 数据
     */
    private List<ShopBean> flist;

    public SimpleAdapter(Context mContext, List<ShopBean> flist) {
        super(mContext, flist);
        this.mContext =mContext;
        this.flist =flist;
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

    @Override
    protected RecyclerView.ViewHolder createViewHolder(int viewType, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_img,parent,false);
        System.out.println("createViewHolder---");
        return new ViewHolde(view);
    }

    @Override
    protected void setOnBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ShopBean item = flist.get(position);
        System.out.println("setOnBindViewHolder---");
        if (viewHolder instanceof ViewHolde){
            ((ViewHolde) viewHolder).shop_name.setText(item.getShopName());
            ((ViewHolde) viewHolder).tv_unit.setText(String.format(Locale.CHINA, "商家介绍：%s", item.getShopDescrition()));

            if(scroll){//滚动不加载图片
                ((ViewHolde) viewHolder).imageView.setImageResource(R.mipmap.ic_launcher);
            }else {//加载图片
                Glide.with(mContext).
                        load(item.getPicture_loacal())
                        .centerCrop()
                        .into(((ViewHolde) viewHolder).imageView);
            }

        }
    }

    @Override
    public int getItemCount() {
        return flist.size();
    }
}
