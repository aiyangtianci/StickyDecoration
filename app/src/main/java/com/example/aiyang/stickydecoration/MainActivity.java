package com.example.aiyang.stickydecoration;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.aiyang.stickydecoration.adapter.RecyclerAdapter;
import com.example.aiyang.stickydecoration.bean.data;
import com.example.aiyang.stickydecoration.view.StickyItemDecoration;
import com.example.aiyang.stickydecoration.view.UpPullOnScrollListener;
import com.example.aiyang.stickydecoration.view.UpPullRecyclerViewOnScrollListener;

public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.golistpage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ListScrollListenerActivity.class));
            }
        });

        findViewById(R.id.goshelfpage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,GoodsShelfActivity.class));
            }
        });
    }

}
