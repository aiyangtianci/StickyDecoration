package com.example.aiyang.stickydecoration.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
public class ShopAdapter extends BaseAdapter<ShopBean,RecyclerView.ViewHolder> {

    /**
     * context
     */
    private Context mContext;
    /**
     * 数据
     */
    private List<ShopBean> flist;

    public ShopAdapter(Context mContext, List<ShopBean> flist) {
        super(mContext, flist);
        this.mContext =mContext;
        this.flist =flist;
    }

    class ViewHolde extends RecyclerView.ViewHolder {
        TextView shop_name;
        TextView tv_unit;
        ImageView imageView ;
        LinearLayout description_ll;
        public ViewHolde(View itemView) {
            super(itemView);
            shop_name =itemView.findViewById(R.id.shop_name);
            tv_unit = itemView.findViewById(R.id.shop_description);
            imageView = itemView.findViewById(R.id.shop_img);
            description_ll= itemView.findViewById(R.id.description_ll);
        }
    }

    @Override
    protected RecyclerView.ViewHolder createViewHolder(int viewType, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_img,parent,false);
        return new ViewHolde(view);
    }

    @Override
    protected void setOnBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ShopBean item = flist.get(position);
        if (viewHolder instanceof ViewHolde){
            final int index = position;
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(index);
                    }
                }
            });
            if (position == 0){
                ((ViewHolde) viewHolder).description_ll.setVisibility(View.GONE);
            }else{
                ((ViewHolde) viewHolder).description_ll.setVisibility(View.VISIBLE);
                ((ViewHolde) viewHolder).shop_name.setText(item.getShopName());
                ((ViewHolde) viewHolder).tv_unit.setText(String.format(Locale.CHINA, "商家介绍：%s", item.getShopDescrition()));
            }

            if(scroll){//滚动不加载图片
                ((ViewHolde) viewHolder).imageView.setImageResource(R.drawable.ic_launcher_foreground);
            }else {//加载图片
                Glide.with(mContext).
                        load(item.getPicture_loacal())
                        .centerCrop()
                        .into(((ViewHolde) viewHolder).imageView);
            }

        }
    }

    //定义接口
    public interface OnItemClickListener {
        void onClick(int position);
    }

    private OnItemClickListener listener;

    // 写一个公共的方法
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
