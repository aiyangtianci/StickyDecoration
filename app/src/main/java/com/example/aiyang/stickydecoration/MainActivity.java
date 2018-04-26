package com.example.aiyang.stickydecoration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.aiyang.stickydecoration.adapter.RecyclerAdapter;
import com.example.aiyang.stickydecoration.bean.data;
import com.example.aiyang.stickydecoration.view.StickyItemDecoration;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView  =findViewById(R.id.recyclelist);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new StickyItemDecoration());
        mRecyclerView.setAdapter(new RecyclerAdapter(this, data.getDataList()));

    }
}
