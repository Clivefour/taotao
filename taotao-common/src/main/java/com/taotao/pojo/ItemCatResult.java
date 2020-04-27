package com.taotao.pojo;

import java.io.Serializable;
import java.util.List;

public class ItemCatResult implements Serializable{
    private List<?> data;

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ItemCatResult{" +
                "data=" + data +
                '}';
    }
}
