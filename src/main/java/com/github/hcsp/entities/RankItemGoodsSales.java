package com.github.hcsp.entities;

public class RankItemGoodsSales {
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
