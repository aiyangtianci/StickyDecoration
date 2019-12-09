package com.example.aiyang.stickydecoration.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.aiyang.stickydecoration.R;
import com.example.aiyang.stickydecoration.bean.GoodCategoryBean;
import com.example.aiyang.stickydecoration.bean.GoodsBean;
import com.example.aiyang.stickydecoration.bean.TypeBean;
import com.example.aiyang.stickydecoration.view.ListContainer;
import com.example.aiyang.stickydecoration.view.ShopCarView;

import java.util.ArrayList;
import java.util.List;

public class GoodsShelfActivity extends AppCompatActivity {

    //自定义点餐列表
    private ListContainer listcontainer;
    private ShopCarView car_mainfl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_layout);

        initView();

        getHTTPJsonData();
    }

    private void initView() {

        listcontainer = findViewById(R.id.listcontainer);
        car_mainfl = findViewById(R.id.car_mainfl);
    }


    /**
     * 请求的数据(假数据)
     */
    private void getHTTPJsonData() {
        List<GoodCategoryBean> data = new ArrayList<>();
        for (int i =0 ;i < 10; i++){
            GoodCategoryBean foodCategory  =new GoodCategoryBean();
            foodCategory.setName("商品种类"+i);
            //为每个子类前，创建一个标题
            List<GoodsBean> goodsList = new ArrayList<>();
            GoodsBean title =new GoodsBean();
            title.setCategoryName(foodCategory.getName());
            title.setItemViewType(1);
            goodsList.add(title);
            //子类
            if (i>1){
                for (int j = 0 ; j <7 ;j ++){
                    GoodsBean food =new GoodsBean();
                    food.setName("普通商品");
                    //网络图片
                    food.setPicture("http://img2.daojia.com.cn/images/littlesheep/food/1e40d55db86158a8f30241e4fa44ed0c.jpg");
                    //本地图片
                    switch (j){
                        case 0:
                            food.setPicture_local(R.mipmap.food_icon1);
                            break;
                        case 1:
                            food.setPicture_local(R.mipmap.food_icon2);
                            break;
                        case 2:
                            food.setPicture_local(R.mipmap.food_icon3);
                            break;
                        case 3:
                            food.setPicture_local(R.mipmap.food_icon4);
                            break;
                        case 4:
                            food.setPicture_local(R.mipmap.food_icon5);
                            break;
                        case 5:
                            food.setPicture_local(R.mipmap.food_icon6);
                            break;
                        case 6:
                            food.setPicture_local(R.mipmap.food_icon7);
                            break;
                    }
                    food.setSubFood(2);
                    food.setUnit("份");
                    food.setPrice(39);
                    food.setItemViewType(2);
                    goodsList.add(food);
                }
            }

            foodCategory.setGoods(goodsList);
            data.add(foodCategory);
        }
        setFoodCategoryList(data);
    }

    /**
     * 处理数据集合
     */
    private void setFoodCategoryList(List<GoodCategoryBean> list) {
        //1、左：菜单种类列表
        List<TypeBean> typeList = new ArrayList<>();
        //2、右：菜品列表集合
        List<GoodsBean> foodList = new ArrayList<>();

        for (GoodCategoryBean bean : list) {
            //1、添加种类名称
            TypeBean typeBean = new TypeBean();
            typeBean.setName(bean.getName());
            typeList.add(typeBean);
            //2、提加所有菜品
            foodList.addAll(bean.getGoods());
        }
        if (listcontainer!=null){
            //设置左适配器
            listcontainer.setTypeAdapterData(typeList);
            //设置右适配器
            listcontainer.setDishAdapterData(foodList);

            car_mainfl.setCarAdapterData(foodList);
            car_mainfl.updateAmount(180,3);
        }
    }
}
