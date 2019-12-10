package com.example.aiyang.stickydecoration.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.aiyang.stickydecoration.R;
import com.example.aiyang.stickydecoration.bean.GoodsBean;
import com.example.aiyang.stickydecoration.commen.DialogUtil;

/**
 * 加减按钮
 */
public class AddWidget extends FrameLayout {


    private TextView tvCount;
    private int count =0;
    private TextView addbutton;
    private TextView subbutton;
    private GoodsBean foodBean;
    private Context mContext;

    public interface OnAddClick {

        void onAddClick(GoodsBean fb);

    }

    private OnAddClick onAddClick;

    /**
     * 设置数据
     */
    public void setGoodData(GoodsBean foodBean,OnAddClick onAddClick) {
        this.foodBean = foodBean;
        this.onAddClick =onAddClick;
    }

    public AddWidget(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public AddWidget(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        inflate(context, R.layout.view_addwidget, this);

        //加法
        addbutton = findViewById(R.id.addbutton);
        addbutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                normalDish(1);
            }
        });
        //数量
        tvCount = findViewById(R.id.tv_count);
        //减法
        subbutton = findViewById(R.id.iv_sub);
        subbutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                normalDish(-1);
            }
        });
        setUIVisible();
    }

    /**
     * 普通菜品处理
     */
    private void normalDish(int num) {
        count = count + num;
        setUIVisible();
        foodBean.setSelectNum(count);
        tvCount.setText(String.valueOf(count));
        onAddClick.onAddClick(foodBean);
    }

    private void setUIVisible(){
        if (count <= 0) {
            tvCount.setVisibility(GONE);
            subbutton.setVisibility(GONE);
        } else {
            tvCount.setVisibility(VISIBLE);
            subbutton.setVisibility(VISIBLE);
        }
    }
}
