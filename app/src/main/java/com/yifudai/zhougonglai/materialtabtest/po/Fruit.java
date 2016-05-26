package com.yifudai.zhougonglai.materialtabtest.po;

/**
 * Created by zhougonglai on 16/4/19.
 */
public class Fruit {
    private String name;
    private int imageId;

    public Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
