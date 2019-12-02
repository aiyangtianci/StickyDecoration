package com.example.aiyang.stickydecoration.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.aiyang.stickydecoration.R;
import com.example.aiyang.stickydecoration.adapter.DishAdapter;
import com.example.aiyang.stickydecoration.adapter.TypeAdapter;
import com.example.aiyang.stickydecoration.bean.GoodsBean;
import com.example.aiyang.stickydecoration.bean.TypeBean;

import java.util.ArrayList;
import java.util.List;

public class ListContainer extends LinearLayout {

    /**
     * 数据
     */
    private List<TypeBean> mTypeBeanData = new ArrayList<>();
    private List<GoodsBean> mFoodBeanData = new ArrayList<>();
    private Context mContext;
    private TypeAdapter typeAdapter;
    private DishAdapter dishAdapter;
    /**
     * 右侧菜品列表
     */
    private RecyclerView recyclerView2;

    public ListContainer(Context context) {
        super(context);
        mContext = context;
    }

    public ListContainer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        inflate(mContext, R.layout.view_listcontainer, this);
        initMenuType();
        initDishList();
    }

    /**
     * 菜单类型（左侧）
     */
    private void initMenuType() {
        RecyclerView typeRecyclerView = findViewById(R.id.recycler1);
        typeRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        typeAdapter = new TypeAdapter(mContext, mTypeBeanData, typeRecyclerView);
        typeRecyclerView.setAdapter(typeAdapter);
        typeAdapter.addOnItemTouchListener(new onItemClickListener() {
            @Override
            public void onSimpleItemClick(View view, int index) {
                //设置选中
                typeAdapter.setChecked(index);
                //获取名称
                String typeName = view.getTag().toString();
                //联动右侧
                for (int position = 0; position < mFoodBeanData.size(); position++) {
                    GoodsBean titleGood = mFoodBeanData.get(position);
                    if (titleGood.getItemViewType() == 1 && titleGood.getCategoryName().equals(typeName)) {
                        LinearLayoutManager ll = (LinearLayoutManager) recyclerView2.getLayoutManager();
                        ll.scrollToPositionWithOffset(position,0);
//                        moveToPosition(position);
                        break;
                    }
                }
            }
        });
    }

//    private void moveToPosition(int n) {
//        //先从RecyclerView的LayoutManager中获取第一项和最后一项的Position
//        LinearLayoutManager ll = (LinearLayoutManager) recyclerView2.getLayoutManager();
//        int firstItem = ll.findFirstVisibleItemPosition();
//        int lastItem = ll.findLastVisibleItemPosition();
//        //然后区分情况
//        if (n <= firstItem) {
//            //当要置顶的项在当前显示的第一个项的前面时
//            recyclerView2.scrollToPosition(n);
//        } else if (n <= lastItem) {
//            //当要置顶的项已经在屏幕上显示时
//            int top = recyclerView2.getChildAt(n - firstItem).getTop();
//            recyclerView2.scrollBy(0, top);
//        } else {
//            //当要置顶的项在当前显示的最后一项的后面时
//            recyclerView2.smoothScrollToPosition(n);
//            //这里这个变量是用在RecyclerView滚动监听里面的
////            move = true;
//        }
//    }

    /**
     * 商品列表（右侧）
     */
    private void initDishList() {
        recyclerView2 = findViewById(R.id.recycler2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(mContext));
        dishAdapter = new DishAdapter(mContext, recyclerView2, mFoodBeanData);
        recyclerView2.setAdapter(dishAdapter);

    }

    /**
     * 设置左侧数据
     */
    public void setTypeAdapterData(List<TypeBean> types) {
        mTypeBeanData.clear();
        mTypeBeanData.addAll(types);
        typeAdapter.setNewData();
        typeAdapter.notifyDataSetChanged();
    }

    /**
     * 设置右侧数据
     */
    public void setDishAdapterData(List<GoodsBean> foods) {
        mFoodBeanData.clear();
        mFoodBeanData.addAll(foods);
        dishAdapter.notifyDataSetChanged();
        recyclerView2.addItemDecoration(new ItemDecoration(foods));
    }

    /**
     * 设置右侧种类名称
     *
     * @param headerViewText
     */
    public void setFirstStickyHeaderViewText(String headerViewText) {
    }

}
