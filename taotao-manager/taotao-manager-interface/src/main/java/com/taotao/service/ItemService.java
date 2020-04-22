package com.taotao.service;

import com.taotao.pojo.LayuiResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
    TbItem findTbItemById(Long itemId);

    LayuiResult findTbItemByPage(int page,int limit);

}
