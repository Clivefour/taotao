package com.taotao.search.service;

import com.taotao.pojo.SearchResult;
import com.taotao.pojo.TaotaoResult;

public interface SearchService {
    TaotaoResult importSolr();

    /**
     * 根据页面传递过来的条件来搜索商品信息
     * @param query 搜索内容
     * @param page 当前页
     * @return 搜索对象
     */
    SearchResult findItemSearch(String query, Integer page);
}
