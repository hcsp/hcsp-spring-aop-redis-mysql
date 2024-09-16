package com.github.hcsp.entity;

import java.io.Serializable;

public class GoodsItem implements Serializable {
    private Integer id;
    private String name;
    private Integer price;

    public GoodsItem() {
    }

    public GoodsItem(Integer id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
