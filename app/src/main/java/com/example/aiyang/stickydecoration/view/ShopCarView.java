package com.example.aiyang.stickydecoration.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
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
    private Context mContext;
    private BottomSheetBehavior behavior;//购物车列表布局
    private List<GoodsBean> mFoodBeanData = new ArrayList<>();//购物车数据
    public boolean sheetScrolling;//滑动中
    private ShopCarAdapter CarAdapter;//适配器
    private RecyclerView car_recyclerview;
    public int[] carLocation;

    public ShopCarView(@NonNull Context context) {
        super(context);
        mContext = context;
    }


    public ShopCarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        inflate(mContext, R.layout.activity_car, this);

        initShopCarList();

        setBehavior();
    }

    /**
     * 初始化购物车列表
     */
    private void initShopCarList() {
        car_recyclerview = findViewById(R.id.car_recyclerview);
        car_recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        car_recyclerview.setItemAnimator(new DefaultItemAnimator());//设置Item增加、移除动画
        CarAdapter = new ShopCarAdapter(mContext, mFoodBeanData);
        car_recyclerview.setAdapter(CarAdapter);
    }

    /**
     * 设置购物车数据
     */
    public void setCarAdapterAddData(GoodsBean food) {
        mFoodBeanData.add(food);
        CarAdapter.notifyItemInserted(mFoodBeanData.indexOf(food));//插入一个并刷新，正常

        updateAmount();//刷新底部购物车展示信息
    }


    /**
     * 行为处理
     */
    public void setBehavior() {
        behavior = BottomSheetBehavior.from(findViewById(R.id.car_container));
        final View blackview = findViewById(R.id.blackview);

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

        findViewById(R.id.car_rl).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sheetScrolling) return;//防止滑动中多次点击
                if (mFoodBeanData.size() == 0) return;//购物车为空
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
    public void updateAmount() {
        TextView carBadge = findViewById(R.id.car_badge); //数量
        TextView selected = findViewById(R.id.car_limit);//选好了按钮

        int ShopCarNum = mFoodBeanData.size();
        double PriceMount  = 0;
        for (int i=0 ; i<ShopCarNum;i++){
            GoodsBean bean = mFoodBeanData.get(i);
            PriceMount +=bean.getPrice();
        }
        if (ShopCarNum <= 0 || PriceMount < 0) {
            selected.setClickable(false);
            carBadge.setVisibility(View.INVISIBLE);
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            selected.setClickable(true);
            carBadge.setVisibility(View.VISIBLE);
            carBadge.setText(String.valueOf(ShopCarNum));
        }

        TextView priceAmount = findViewById(R.id.tv_amount);//价格
        priceAmount.setText("￥ " + String.format(Locale.CHINA, "%.2f", PriceMount));

    }

}
