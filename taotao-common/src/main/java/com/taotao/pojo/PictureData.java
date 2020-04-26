package com.taotao.pojo;

import java.io.Serializable;

public class PictureData implements Serializable{
    private String src;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return "PictureData{" +
                "src='" + src + '\'' +
                '}';
    }
}
