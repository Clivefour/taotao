package com.taotao.content.service;

import com.taotao.pojo.*;

import java.util.List;

public interface ItemContentService {
    List<ZtreeResult> getZtreeResult(Long id);

    /**
     * 根据内容分类id查询内容信息
     * @param categoryId 内容分类id
     * @param page 当前页
     * @param limit 每一页显示条数
     * @return layui需要的json格式
     */
    LayuiResult findContentByCategoryId(Long categoryId, Integer page, Integer limit);

    /**
     * 根据内容id删除指定内容信息
     * @param tbContents 需要删除的内容集合对象
     * @param page 当前页
     * @param limit 每一页显示条数
     * @return
     */
    LayuiResult deleteContentByCategoryId(List<TbContent> tbContents, Integer page, Integer limit);

    /**
     * 新添加一个内容信息
     * @param tbContent
     * @param page
     * @param limit
     * @return
     */
    TaotaoResult addContent(TbContent tbContent, Integer page, Integer limit);

    List<Ad1Node> showAd1Node();
}
