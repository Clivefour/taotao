package com.taotao.portal.controller;

import com.alibaba.dubbo.common.json.JSON;
import com.taotao.content.service.ItemContentService;
import com.taotao.pojo.Ad1Node;
import com.taotao.pojo.ItemCatResult;
import com.taotao.service.ItemCatService;
import com.taotao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private ItemCatService itemCatService;
    @Autowired
    private ItemContentService itemContentService;

    @RequestMapping("/index")
    public String showIndex(Model model){
        /**
         * 因为你知道 首页有哪些 内容
         * 89大广告   xxx 90 xxxx 91
         * 调用一个service 查询数据库 得到结果集
         * 组装成为 页面需要的json 发送到页面
         */
        //在来一个方法 只根据内容分类id 查询内容信息 的方法
        List<Ad1Node> nodes = itemContentService.showAd1Node();
        String ad1 = JsonUtils.objectToJson(nodes);
        model.addAttribute("ad1",ad1);
        return "index";
    }
    @RequestMapping("/itemCat/all")
    @ResponseBody
    public String showItemCat(){
        ItemCatResult result =  itemCatService.getItemCats();
        String json = JsonUtils.objectToJson(result);
        return json;
    }
}

