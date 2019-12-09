package com.example.aiyang.stickydecoration.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aiyang.stickydecoration.R;
import com.example.aiyang.stickydecoration.adapter.ShopCarAdapter;
import com.example.aiyang.stickydecoration.bean.GoodsBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ShopCarView extends FrameLayout {
    private BottomSheetBehavior behavior;
    private Context mContext;
    private List<GoodsBean> mFoodBeanData = new ArrayList<>();
    public boolean sheetScrolling;
    private ShopCarAdapter CarAdapter;
    public int[] carLocation;
    private View blackview;
    private RelativeLayout shoprl;
    private RecyclerView car_recyclerview;
    private int ShopCarNum =0;

    public ShopCarView(@NonNull Context context) {
        super(context);
        mContext = context;
    }


    public ShopCarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        inflate(mContext,R.layout.activity_car,this);

        initShopCarList();

        setBehavior();
    }

    /**
     * 初始化购物车列表
     */
    private void initShopCarList() {
        car_recyclerview = findViewById(R.id.car_recyclerview);
        car_recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        CarAdapter = new ShopCarAdapter(mContext, mFoodBeanData);
        car_recyclerview.setAdapter(CarAdapter);
    }

    /**
     * 设置购物车数据
     */
    public void setCarAdapterData(List<GoodsBean> foods) {
        mFoodBeanData.clear();
        mFoodBeanData.addAll(foods);
        CarAdapter.notifyDataSetChanged();
    }

    /**
     * 设置滑动
     */
    public void setBehavior() {
        behavior = BottomSheetBehavior.from(findViewById(R.id.car_container));
        blackview = findViewById(R.id.blackview);

        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                sheetScrolling = false;
                if (newState == BottomSheetBehavior.STATE_COLLAPSED || newState == BottomSheetBehavior.STATE_HIDDEN) {
                    blackview.setVisibility(View.GONE);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                sheetScrolling = true;
                blackview.setVisibility(View.VISIBLE);
                ViewCompat.setAlpha(blackview, slideOffset);
            }
        });

        blackview.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                return true;
            }
        });

        shoprl = findViewById(R.id.car_rl);
        shoprl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sheetScrolling) return;//防止滑动中多次点击
                if (ShopCarNum <= 0) return;//购物车为空
                if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });
    }

    /**
     * 设置购物车数量和价格
     */
    public void updateAmount(float amount, int total) {
        ShopCarNum = total;

        TextView  selected = findViewById(R.id.car_limit);//选好了
        if (total <= 0 || amount < 0) {
            selected.setClickable(false);
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            selected.setClickable(true);
        }

        TextView  priceAmount = findViewById(R.id.tv_amount);//价格
        priceAmount.setText("￥ " + String.format(Locale.CHINA, "%.2f", amount));

        TextView carBadge = findViewById(R.id.car_badge); //数量
        if (total > 0) {
            carBadge.setVisibility(View.VISIBLE);
            carBadge.setText(String.valueOf(total));
        } else {
            carBadge.setVisibility(View.INVISIBLE);
        }
    }

}
