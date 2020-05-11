package com.taotao.item.controller;

import com.taotao.item.pojo.Item;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamGroup;
import com.taotao.pojo.TbItemParamKey;
import com.taotao.service.ItemService;
import com.taotao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
        List<TbItemParamGroup> groupList = itemService.findTbItemGroupByItemId(itemId);
        StringBuffer sb = new StringBuffer();
        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
        sb.append("    <tbody>\n");
        for (TbItemParamGroup group:groupList) {
            sb.append("        <tr>\n");
            sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+group.getGroupName()+"</th>\n");
            sb.append("        </tr>\n");
            List<TbItemParamKey> paramKeys = group.getParamKeys();
            for (TbItemParamKey paramKey:paramKeys) {
                sb.append("        <tr>\n");
                sb.append("            <td class=\"tdTitle\">"+paramKey.getParamName()+"</td>\n");
                sb.append("            <td>"+paramKey.getItemParamValue().getParamValue()+"</td>\n");
                sb.append("        </tr>\n");
            }

        }
        sb.append("    </tbody>\n");
        sb.append("</table>");
        return sb.toString();
    }

}
