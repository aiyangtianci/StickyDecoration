package com.example.aiyang.stickydecoration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.aiyang.stickydecoration.demo.CoordinatorActivity;
import com.example.aiyang.stickydecoration.demo.ListScrollListenerActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.golistpage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListScrollListenerActivity.class));
            }
        });

        findViewById(R.id.goshelfpage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GoodsShelfActivity.class));
            }
        });

        findViewById(R.id.coordinatorpage).setOnClickListener(new View.OnClickListener() {
                                                                  @Override
                                                                  public void onClick(View view) {
                                                                      startActivity(new Intent(MainActivity.this, CoordinatorActivity.class
                                                                      ));
                                                                  }
                                                              }
        );
    }

}
