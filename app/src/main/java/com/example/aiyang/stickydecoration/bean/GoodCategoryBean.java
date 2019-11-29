package com.example.aiyang.stickydecoration.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 数据
 */
public class GoodCategoryBean implements Serializable{

    private String name;
    private List<GoodsBean> goods;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }
}
