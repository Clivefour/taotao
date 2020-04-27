package com.taotao.service.impl;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.ItemCat;
import com.taotao.pojo.ItemCatResult;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.ZtreeResult;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    @Override
    public List<ZtreeResult> getZtreeResult(Long id) {
        List<TbItemCat> tbItemCats = tbItemCatMapper.findTbItemCatByParentId(id);
        List<ZtreeResult> results = new ArrayList<ZtreeResult>();
        for (TbItemCat tbItemCat: tbItemCats) {
            ZtreeResult result = new ZtreeResult();
            result.setId(tbItemCat.getId());
            result.setName(tbItemCat.getName());
            result.setIsParent(tbItemCat.getIsParent());
            results.add(result);
        }
        return results;
    }

    @Override
    public ItemCatResult getItemCats() {
        ItemCatResult result = new ItemCatResult();
        result.setData(getItemCatList(0L));
        return result;
    }
    //
    private List<?> getItemCatList(Long parentId){
        // parentId = 0
        int count = 0;
        List list = new ArrayList();
        List<TbItemCat> tbItemCats = tbItemCatMapper.findTbItemCatByParentId(parentId);
        //遍历一级类目
        for (TbItemCat itemCat:tbItemCats) {
            //   id = 1        name = 图书、音像、电子书刊
            ItemCat item = new ItemCat();
            //判断他只父级目录还是最底级目录
            if(itemCat.getIsParent()){
                //代表一定是第一级或者第二级类目
                item.setUrl("/products/"+itemCat.getId()+".html");
                if(itemCat.getParentId()==0){
                    //"n":"<a href='/products/1.html'>图书、音像、电子书刊</a>",
                    item.setName("<a href='/products/"+itemCat.getId()+".html'>"+itemCat.getName()+"</a>");
                }else{
                    //一定是第二级类目
                    item.setName(itemCat.getName());
                }
                    //设置在设置i
                    item.setItem(getItemCatList(itemCat.getId()));
                    //
                    list.add(item);
                    count++;
                if (parentId == 0 && count >= 14) {
                    break;
                }

            }else{
                //一定是最底级目录 一定是三级类目
                list.add("/products/"+itemCat.getId()+".html|"+itemCat.getName());
            }
        }
        return list;
    }
}
