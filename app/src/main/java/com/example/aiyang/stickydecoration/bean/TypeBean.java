package com.example.aiyang.stickydecoration.bean;

import java.io.Serializable;

/**
 * 类型
 * name 类名
 * typeId 种类的Id
 * @author aiyang
 */
public class TypeBean implements Serializable {
    private String name;
    private int typeId;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "TypeBean{" +
                "name='" + name + '\'' +
                ", typeId=" + typeId +
                '}';
    }


    //添加一个算法选中的标识

    private boolean isChecked =false;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
