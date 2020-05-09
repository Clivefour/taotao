package com.taotao.service;

import com.taotao.pojo.TaotaoResult;

public interface ItemGroupService {
    TaotaoResult findTbItemGroupByCId(Long cId);

    /**
     * 根据商品分类id 添加商品规格参数组合规格参数项
     * @param cId 商品分类id
     * @param params 包含了组和项
     * @return
     */
    TaotaoResult addGroup(Long cId, String params);
}
