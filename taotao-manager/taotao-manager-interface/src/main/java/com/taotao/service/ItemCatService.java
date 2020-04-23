package com.taotao.service;

import com.taotao.pojo.ZtreeResult;

import java.util.List;

//商品分类服务
public interface ItemCatService {
    /**
     * 根据当前id 来作为参数 查询 这个分类下的子集
     * @param id 当前节点id
     * @return Ztree需要的json格式
     */
    List<ZtreeResult> getZtreeResult(Long id);
}
