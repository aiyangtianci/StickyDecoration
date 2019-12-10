package com.example.aiyang.stickydecoration.bean;

public class GoodsBean {


    private int picture_local;
    private String unit;//单位名称
    private double price;

    private String name;
    private String picture;

    private int SubFood;//1是套餐，2是普通
    /**
     * 子项类型
     * 1: 标题
     * 2：普通
     */
    private int ItemViewType;
    private String CategoryName;
    private String description;//商品介绍
    private int FoodTag;//1是标签多规格，2是普通

    private int selectNum;//已选择数量




//    ----------------GET-------SET-----------------

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSelectNum() {
        return selectNum;
    }

    public void setSelectNum(int selectNum) {
        this.selectNum = selectNum;
    }

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

    public int getPicture_local() {
        return picture_local;
    }

    public void setPicture_local(int picture_local) {
        this.picture_local = picture_local;
    }

    public void setSubFood(int subFood) {
        SubFood = subFood;
    }


    public int getFoodTag() {
        return FoodTag;
    }

    public void setFoodTag(int foodTag) {
        FoodTag = foodTag;
    }

    public int getSubFood() {
        return SubFood;
    }

    public int getItemViewType() {
        return ItemViewType;
    }

    public void setItemViewType(int itemViewType) {
        ItemViewType = itemViewType;
    }


}
