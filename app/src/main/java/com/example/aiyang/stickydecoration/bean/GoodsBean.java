package com.example.aiyang.stickydecoration.bean;

public class GoodsBean {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    private String name;
    private String picture;

    public int getPicture_local() {
        return picture_local;
    }

    public void setPicture_local(int picture_local) {
        this.picture_local = picture_local;
    }

    private int  picture_local;
    private String unit;
    private double price;


    /**
     * 子项类型
     * 1: 标题
     * 2：普通
     */
    private int ItemViewType;
    private String CategoryName;

    public int getItemViewType() {
        return ItemViewType;
    }

    public void setItemViewType(int itemViewType) {
        ItemViewType = itemViewType;
    }

    private int SubFood;//1是套餐，2是普通

    public int getSubFood() {
        return SubFood;
    }

    public void setSubFood(int subFood) {
        SubFood = subFood;
    }
}
