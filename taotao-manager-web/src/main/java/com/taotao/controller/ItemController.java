package com.taotao.controller;

import com.taotao.pojo.LayuiResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    /**
     * 我们的请求路径为 http://localhost:8080/taotao-manager-web/item/参数（商品id）
     * @return
     */
    @RequestMapping("/{itemId}")
    @ResponseBody
    public TbItem findTbItem(@PathVariable Long itemId){
        TbItem result = itemService.findTbItemById(itemId);
        return result;
    }
    //http://localhost:8081/item/showItemPage?page=1&limit=10
    @RequestMapping("/showItemPage")
    @ResponseBody
    public LayuiResult showItemPage(Integer page,Integer limit){
        LayuiResult result = itemService.findTbItemByPage(page, limit);
        return result;
    }
}
