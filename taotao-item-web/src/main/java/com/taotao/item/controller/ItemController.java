package com.taotao.item.controller;

import com.taotao.item.pojo.Item;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;


    @RequestMapping("/{itemId}")
    public String showItem(@PathVariable Long itemId, Model model){
        TbItem tbItem = itemService.findTbItemById(itemId);
        Item item = new Item(tbItem);
        model.addAttribute("item",item);
        return "item";
    }
    @RequestMapping("/desc/{itemId}")
    @ResponseBody
    public String showItemDesc(@PathVariable Long itemId){
        TbItemDesc itemDesc = itemService.findTbItemDescByItemId(itemId);

        return itemDesc.getItemDesc();
    }
    @RequestMapping("/param/{itemId}")
    @ResponseBody
    public String showItemParam(@PathVariable Long itemId){
        return null;
    }

}
