package com.taotao.content.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.dubbo.common.json.JSON;
import com.taotao.content.service.ItemContentService;
import com.taotao.content.service.JedisClient;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.*;
import com.taotao.utils.IDUtils;
import com.taotao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class ItemContentServiceImpl implements ItemContentService {
    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;
    @Autowired
    private TbContentMapper tbContentMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("AD1")
    private String AD1;

    @Override
    public List<ZtreeResult> getZtreeResult(Long id) {
        List<ZtreeResult> results = new ArrayList<ZtreeResult>();
        List<TbContentCategory> tbContentCategorys = tbContentCategoryMapper.findContentByParentId(id);
        for (TbContentCategory tbContentCategory:tbContentCategorys) {
            ZtreeResult ztreeResult = new ZtreeResult();
            ztreeResult.setId(tbContentCategory.getId());
            ztreeResult.setName(tbContentCategory.getName());
            ztreeResult.setIsParent(tbContentCategory.getIsParent());
            results.add(ztreeResult);
        }
        return results;
    }

    @Override
    public LayuiResult findContentByCategoryId(Long categoryId, Integer page, Integer limit) {
        LayuiResult result = new LayuiResult();
        result.setCode(0);
        result.setMsg("");
        int count = tbContentMapper.findContentByCount(categoryId);
        result.setCount(count);
        List<TbContent> data = tbContentMapper.findContentByPage(categoryId,(page-1)*limit,limit);
        result.setData(data);
        return result;
    }

    @Override
    public LayuiResult deleteContentByCategoryId(List<TbContent> tbContents, Integer page, Integer limit) {
        LayuiResult result = new LayuiResult();
        result.setCode(0);
        result.setMsg("");
        result.setCount(0);
        result.setData(null);
        if(tbContents.size()<=0){
            return result;
        }
        int i = tbContentMapper.deleteContentByCategoryId(tbContents);
        if(i<=0){
            return result;
        }
        //因为你要删除那个分类 他吧集合传递过来了
        Long categoryId = tbContents.get(0).getCategoryId();

        int count = tbContentMapper.findContentByCount(categoryId);
        if(count<=0){
            return result;
        }

        result.setCount(count);
        List<TbContent> data = tbContentMapper.findContentByPage(categoryId,(page-1)*limit,limit);
        result.setData(data);
        jedisClient.del(AD1);
        return result;
    }

    @Override
    public TaotaoResult addContent(TbContent tbContent, Integer page, Integer limit) {
        Date date = new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        tbContentMapper.addContent(tbContent);
        jedisClient.del(AD1);
        return TaotaoResult.build(200,"添加成功");
    }

    @Override
    public List<Ad1Node> showAd1Node() {
        String json = jedisClient.get(AD1);
        if(StringUtils.isNotBlank(json)){
            List<Ad1Node> nodes = JsonUtils.jsonToPojo(json,List.class);
            return nodes;
        }


        List<Ad1Node> nodes = new ArrayList<Ad1Node>();
        List<TbContent> contents = tbContentMapper.findContentByPage(89L, 0, 10);
        for (TbContent content:contents) {
            Ad1Node node = new Ad1Node();
            node.setSrcB(content.getPic2());
            node.setHeight(240);
            node.setAlt(content.getTitleDesc());
            node.setWidth(670);
            node.setSrc(content.getPic());
            node.setWidthB(550);
            node.setHref(content.getUrl());
            node.setHeightB(240);
            nodes.add(node);
        }
        jedisClient.set(AD1, JsonUtils.objectToJson(nodes));

        return nodes;
    }
}
