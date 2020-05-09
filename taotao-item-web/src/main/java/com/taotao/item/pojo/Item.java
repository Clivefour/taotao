package com.taotao.item.pojo;

import com.taotao.pojo.TbItem;

public class Item extends TbItem {
    public String[] getImages() {
        String image2 = this.getImage();
        if (image2 != null && !"".equals(image2)) {
            String[] strings = image2.split("http");
            String[] images = new String[strings.length-1];
            for(int i = 1; i<strings.length;i++){
                images[i-1] = "http"+strings[i];
            }
            return images;
        }
        return null;
    }



    public Item() {

    }

    public Item(TbItem tbItem) {
        this.setBarcode(tbItem.getBarcode());
        this.setcId(tbItem.getcId());
        this.setCreated(tbItem.getCreated());
        this.setId(tbItem.getId());
        this.setImage(tbItem.getImage());
        this.setNum(tbItem.getNum());
        this.setPrice(tbItem.getPrice());
        this.setSellPoint(tbItem.getSellPoint());
        this.setStatus(tbItem.getStatus());
        this.setTitle(tbItem.getTitle());
        this.setUpdated(tbItem.getUpdated());
    }

}
