package com.example.aiyang.stickydecoration.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aiyang.stickydecoration.R;
import com.example.aiyang.stickydecoration.adapter.ShopCarAdapter;
import com.example.aiyang.stickydecoration.bean.GoodsBean;
import com.example.aiyang.stickydecoration.commen.CommonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ShopCarView extends FrameLayout {
    private BottomSheetBehavior behavior;
    private Context mContext;
    private List<GoodsBean> mFoodBeanData = new ArrayList<>();
    public ImageView ivCartView;
    private TextView carBadge;
    private TextView selected;
    private TextView priceAmount;
    public boolean sheetScrolling;
    private int total;
    private ShopCarAdapter CarAdapter;
    public int[] carLocation;
    private View blackview;
    private RelativeLayout shoprl;
    private RecyclerView car_recyclerview;

    public ShopCarView(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    public ShopCarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        findViewById();
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //设置滑动监听
        setBehavior();
    }

    private void findViewById() {
        ivCartView = findViewById(R.id.iv_shop_car);
        carBadge = findViewById(R.id.car_badge);
        selected = findViewById(R.id.car_limit);
        priceAmount = findViewById(R.id.tv_amount);
    }

    /**
     * 设置购物车数量和价格
     */
    public void updateAmount(float amount, int total) {
        if (amount == 0) {
            selected.setClickable(false);
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            selected.setClickable(true);
        }
        priceAmount.setText("￥ " + String.format(Locale.CHINA, "%.2f", amount));

        this.total = total;

        showBadge(total);
    }

    /**
     * 设置数量
     */
    public void showBadge(int total) {
        if (total > 0) {
            carBadge.setVisibility(View.VISIBLE);
            carBadge.setText(String.valueOf(total));
        } else {
            carBadge.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 设置购物车数据
     */
    public void setCarAdapterData(List<GoodsBean> foods) {
        if (CarAdapter == null) {
            car_recyclerview = findViewById(R.id.car_recyclerview);
            car_recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
            CarAdapter = new ShopCarAdapter(mContext, mFoodBeanData);
            car_recyclerview.setAdapter(CarAdapter);
        }
        mFoodBeanData.clear();
        mFoodBeanData.addAll(foods);
        CarAdapter.notifyDataSetChanged();
    }

    public void setBehavior() {
        behavior = BottomSheetBehavior.from(findViewById(R.id.car_container));
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

        blackview = findViewById(R.id.blackview);
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
                if (sheetScrolling) return;
                if (total == 0) return;
                if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }

            }
        });
    }
}
