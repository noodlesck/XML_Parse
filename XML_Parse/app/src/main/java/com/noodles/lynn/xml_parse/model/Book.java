package com.noodles.lynn.xml_parse.model;

/**
 * Created by Lynn on 2016/4/19.
 */
public class Book {
    private int id;
    private String name;
    private float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
