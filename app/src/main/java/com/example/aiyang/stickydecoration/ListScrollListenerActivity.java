package com.example.aiyang.stickydecoration;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.aiyang.stickydecoration.adapter.RecyclerAdapter;
import com.example.aiyang.stickydecoration.bean.data;
import com.example.aiyang.stickydecoration.view.StickyItemDecoration;
import com.example.aiyang.stickydecoration.view.UpPullOnScrollListener;
import com.example.aiyang.stickydecoration.view.UpPullRecyclerViewOnScrollListener;

public class ListScrollListenerActivity  extends AppCompatActivity {

    RecyclerView mRecyclerView;
    boolean isHasMore;//是否还有更多（分页加载）
    RecyclerAdapter mAdapter;
    SwipeRefreshLayout mSwipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mRecyclerView  =findViewById(R.id.recyclelist);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new StickyItemDecoration());
        mRecyclerView.setAdapter(mAdapter =new RecyclerAdapter(this, data.getDataList()));
        mRecyclerView.addOnScrollListener(new UpPullRecyclerViewOnScrollListener(onScrollListener));

        mSwipe = (SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        mSwipe.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        mSwipe.setRefreshing(false);
//                        Toast.makeText(MainActivity.this, "下拉更新了 ", Toast.LENGTH_SHORT).show();
                    }
                },3000);
            }
        });
    }
    UpPullOnScrollListener onScrollListener =  new UpPullOnScrollListener() {
        @Override
        public void onLoadMoreData() {

            if (isHasMore) {
                if (mAdapter.isLoadState()) {
                    mAdapter.setLoadState(mAdapter.LOADING);//显示加载
                }
            } else {
                mAdapter.setLoadState(mAdapter.LOADING_END);//没有更多
            }
            Toast.makeText(ListScrollListenerActivity.this, "上拉更新了 ", Toast.LENGTH_SHORT).show();
            getMoreData();
        }

        @Override
        public void onRefreshData() {
            Toast.makeText(ListScrollListenerActivity.this, "下拉刷新了 ", Toast.LENGTH_SHORT).show();
        }
    };
    /**
     * 网络请求数据
     */
    private void getMoreData() {
    }
}
