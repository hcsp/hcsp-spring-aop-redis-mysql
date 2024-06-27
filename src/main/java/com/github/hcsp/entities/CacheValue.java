package com.github.hcsp.entities;

import java.io.Serializable;

public class CacheValue implements Serializable {
    private Object data;
    private long updateAt;

    public CacheValue(Object data, long updateAt) {
        this.data = data;
        this.updateAt = updateAt;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(long updateAt) {
        this.updateAt = updateAt;
    }
}
