package com.taotao.content.service.impl;

import com.taotao.content.service.ItemContentService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.LayuiResult;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.ZtreeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ItemContentServiceImpl implements ItemContentService {
    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

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
        int count = tbContentCategoryMapper.findContentByCount(categoryId);
        result.setCount(count);
        List<TbContent> data = tbContentCategoryMapper.findContentByPage(categoryId,(page-1)*limit,limit);
        result.setData(data);
        return result;
    }
}