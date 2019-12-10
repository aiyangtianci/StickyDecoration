package com.example.aiyang.stickydecoration.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.aiyang.stickydecoration.R;
import com.example.aiyang.stickydecoration.bean.GoodCategoryBean;
import com.example.aiyang.stickydecoration.bean.GoodsBean;
import com.example.aiyang.stickydecoration.bean.TypeBean;
import com.example.aiyang.stickydecoration.view.AddWidget;
import com.example.aiyang.stickydecoration.view.ListContainer;
import com.example.aiyang.stickydecoration.view.ShopCarView;

import java.util.ArrayList;
import java.util.List;

public class GoodsShelfActivity extends AppCompatActivity implements AddWidget.OnAddClick {

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
            if (i>0){
                for (int j = 0 ; j <7 ;j ++){
                    GoodsBean food =new GoodsBean();
                    food.setName("普通商品");
                    //网络图片
                    food.setPicture("http://img2.daojia.com.cn/images/littlesheep/food/1e40d55db86158a8f30241e4fa44ed0c.jpg");
                    //本地图片
                    switch (j){
                        case 0:
                            food.setPicture_local(R.mipmap.food_icon1);
                            food.setDescription("新品推荐，夏季佳品！");
                            break;
                        case 1:
                            food.setPicture_local(R.mipmap.food_icon2);
                            food.setDescription("🔥火热，赶紧来一份！");
                            break;
                        case 2:
                            food.setPicture_local(R.mipmap.food_icon3);
                            food.setDescription("换季佳品，优惠半价！");
                            break;
                        case 3:
                            food.setPicture_local(R.mipmap.food_icon4);
                            food.setDescription("门店招牌");
                            break;
                        case 4:
                            food.setPicture_local(R.mipmap.food_icon5);
                            food.setDescription("美味不可挡");
                            break;
                        case 5:
                            food.setPicture_local(R.mipmap.food_icon6);
                            food.setDescription("来一份尝尝");
                            break;
                        case 6:
                            food.setPicture_local(R.mipmap.food_icon7);
                            food.setDescription("打折促销活动，优惠中");
                            break;
                    }
                    food.setSubFood(2);
                    food.setUnit("份");
                    food.setPrice(39);
                    food.setItemViewType(2);
                    goodsList.add(food);
                    if (j%2 == 1){
                        food.setFoodTag(1);
                    }else{
                        food.setFoodTag(2);
                    }
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
            listcontainer.setDishAdapterData(foodList,this);

        }
    }

    @Override
    public void onAddClick(GoodsBean fb) {
        car_mainfl.setCarAdapterAddData(fb);
    }
}
