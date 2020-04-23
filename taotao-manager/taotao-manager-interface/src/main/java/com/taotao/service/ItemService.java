package com.taotao.service;

import com.taotao.pojo.LayuiResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

import java.util.Date;
import java.util.List;
//商品服务
public interface ItemService {
    TbItem findTbItemById(Long itemId);

    LayuiResult findTbItemByPage(int page,int limit);

    /**
     * 批量修改商品信息
     * @param tbItem 需要修改的商品对象集合
     * @param type 如果为0则代表下架 如果为1则表达上架 如果为2则代表删除
     * @param date 当前更新书剑
     * @return 页面需要的json格式  web会解析这个json 吧需要的数据展示在页面上面
     */
    TaotaoResult updateItem(List<TbItem> tbItem, int type, Date date);
}
