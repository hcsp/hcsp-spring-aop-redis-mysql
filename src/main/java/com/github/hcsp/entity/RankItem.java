package com.github.hcsp.entity;

import java.io.Serializable;

public class RankItem implements Serializable {
    private String goodName;
    private int goodTotal;

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public int getGoodTotal() {
        return goodTotal;
    }

    public void setGoodTotal(int goodTotal) {
        this.goodTotal = goodTotal;
    }
}
