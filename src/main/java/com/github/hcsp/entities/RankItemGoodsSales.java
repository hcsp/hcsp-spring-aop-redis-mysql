package com.github.hcsp.entities;

import java.io.Serializable;

public class RankItemGoodsSales implements Serializable {
    private long totalPrice;
    private Goods goods;

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
