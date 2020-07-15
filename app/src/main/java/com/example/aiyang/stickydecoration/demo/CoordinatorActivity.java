package com.example.aiyang.stickydecoration.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.aiyang.stickydecoration.R;
import com.example.aiyang.stickydecoration.adapter.ShopAdapter;
import com.example.aiyang.stickydecoration.bean.ShopBean;
import com.example.aiyang.stickydecoration.commen.CommonUtil;
import com.example.aiyang.stickydecoration.view.AppBarStateChangeListener;
import com.example.aiyang.stickydecoration.view.AutoLoadScrollListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 餐厅toolbar联动
 */
public class CoordinatorActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<ShopBean> sLists = new ArrayList<>();
    private AppBarLayout appbarlayout;
    private Toolbar toolbar;
    private MenuItem item_search;
    private LinearLayout ll_search;
    private float LL_SEARCH_MAX_WIDTH, LL_SEARCH_MIN_WIDTH;
    private ViewGroup.MarginLayoutParams searchLayoutParams;
    //创建GestureDetector实例
    private ShopAdapter simpleAdapter;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        initData();
        appBarViewChange();
        initListView();
    }

    private void appBarViewChange() {
        //搜索框
        ll_search = findViewById(R.id.ll_search);
        LL_SEARCH_MAX_WIDTH = CommonUtil.getScreenWidth(this) - CommonUtil.dp2px(this, 170f);//布局默认展开时的宽度
        LL_SEARCH_MIN_WIDTH = CommonUtil.dp2px(this, 10f);//布局关闭时的宽度
        searchLayoutParams = (ViewGroup.MarginLayoutParams) ll_search.getLayoutParams();
        appbarlayout = findViewById(R.id.appbarlayout);
        //联动
        appbarlayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state, int i) {
                if (state == State.EXPANDED) {
                    //展开状态
                    toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
                    setSearchInvisible();
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
                    setSearchVisible();
                } else {
                    //中间状态
                    toolbar.setNavigationIcon(null);
                    onSearchLayoutParams(appBarLayout.getTotalScrollRange(), Math.abs(i));
                }
            }
        });
    }

    private void initListView(){
        recyclerView = findViewById(R.id.recyclerView);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        RecyclerView.LayoutManager layoutManager =new StaggeredGridLayoutManager(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(simpleAdapter = new ShopAdapter(this, sLists));
        recyclerView.addOnScrollListener(new AutoLoadScrollListener());
        AutoLoadScrollListener.setMaxFlingVelocity(recyclerView,simpleAdapter,14000);
        simpleAdapter.setOnItemClickListener(new ShopAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                startActivity(new Intent(CoordinatorActivity.this,GoodsShelfActivity.class));
            }
        });
    }

    //数据初始化
    private void initData() {
        ShopBean shop;
        for (int i = 0; i < 36; i++) {
            shop = new ShopBean();
            int index = i % 6;
            switch (index) {
                case 0:
                    shop.setPicture_loacal(R.mipmap.shop1);
                    break;

                case 1:
                    shop.setPicture_loacal(R.mipmap.shop6);
                    break;

                case 2:
                    shop.setPicture_loacal(R.mipmap.shop3);
                    break;

                case 3:
                    shop.setPicture_loacal(R.mipmap.shop4);
                    break;

                case 4:
                    shop.setPicture_loacal(R.mipmap.shop5);
                    break;

                case 5:
                    shop.setPicture_loacal(R.mipmap.shop2);
                    break;
            }
            shop.setShopDescrition("这是第 " + i + " 个商家描述，正在促销！点击进入..");
            shop.setShopName("食尚" + i + "号街");
            sLists.add(shop);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        item_search = menu.findItem(R.id.menu_search);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_search).setIcon(android.support.v7.appcompat.R.drawable.abc_ic_search_api_material);
        menu.findItem(R.id.menu_favorite).setIcon(android.support.v7.appcompat.R.drawable.abc_ic_star_black_36dp);
        menu.findItem(R.id.menu_share).setIcon(android.support.v7.appcompat.R.drawable.abc_ic_menu_share_mtrl_alpha);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_search:
                Toast.makeText(this, "嘿！ 就这点数据还想查？", Toast.LENGTH_SHORT).show();
                item.setVisible(false);
                break;

            case R.id.menu_favorite:
                Toast.makeText(this, "嘿 ！眼光不错哦！", Toast.LENGTH_SHORT).show();
                item.setIcon(android.support.v7.appcompat.R.drawable.abc_ic_star_half_black_36dp);
                break;
            case R.id.menu_share:
                Toast.makeText(this, "嘿！ 分享到哪？", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }


    private void onSearchLayoutParams(int appBarLayoutHeight, float dy) {
        float searchLayoutNewWidth = (dy / appBarLayoutHeight) * LL_SEARCH_MAX_WIDTH;
        if (searchLayoutNewWidth <= LL_SEARCH_MIN_WIDTH) {
            setSearchInvisible();
        } else {
            setSearchVisible();
            searchLayoutParams.width = (int) searchLayoutNewWidth;
            ll_search.setLayoutParams(searchLayoutParams);
        }
    }

    private void setSearchVisible() {
        ll_search.setVisibility(View.VISIBLE);
        if (item_search != null) {
            item_search.setVisible(false);
        }

    }

    private void setSearchInvisible() {
        ll_search.setVisibility(View.INVISIBLE);
        if (item_search != null) {
            item_search.setVisible(true);
        }
    }
}
