package com.example.aiyang.stickydecoration;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.example.aiyang.stickydecoration.commen.ThemeUtil;
import com.example.aiyang.stickydecoration.demo.CoordinatorActivity;
import com.example.aiyang.stickydecoration.demo.ListScrollListenerActivity;

public class MainActivity extends AppCompatActivity {
    private MainActivity mContext;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置主题
        ThemeUtil.setBaseTheme(this);
        setContentView(R.layout.activity_main);
        mContext = this;
        Toolbar toolbar= (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        //设置不现实自带的title文字
        getSupportActionBar().setDisplayShowTitleEnabled(false);


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.settings).setIcon(android.support.v7.appcompat.R.drawable.abc_ic_menu_overflow_material);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.settings){
            showPopupWindow(findViewById(R.id.menu_pop));
        }
        return true;
    }

    /**
     * 更多菜单选项弹框
     *
     * @param view
     */
    private void showPopupWindow(View view) {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.pop_window, null);

        contentView.findViewById(R.id.checkblue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ThemeUtil.setNewTheme(mContext, ThemeUtil.ThemeColors.ThEME_BLUE)){
                    recreate();
                }
                popupWindow.dismiss();
            }
        });
        contentView.findViewById(R.id.checkyellow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ThemeUtil.setNewTheme(mContext, ThemeUtil.ThemeColors.THEME_YELLOW))
                    recreate();
                popupWindow.dismiss();
            }
        });
        contentView.findViewById(R.id.checkgreen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ThemeUtil.setNewTheme(mContext, ThemeUtil.ThemeColors.THEME_GREEN))
                    recreate();
                popupWindow.dismiss();
            }
        });
        contentView.findViewById(R.id.checkgrey).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ThemeUtil.setNewTheme(mContext, ThemeUtil.ThemeColors.THEME_GREY))
                    recreate();
                popupWindow.dismiss();
            }
        });
        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setBackgroundDrawable(new ColorDrawable(0000000000));
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true); // 点击外部关闭
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置好参数之后再show
        popupWindow.showAsDropDown(view, 0, 30);
    }

}
