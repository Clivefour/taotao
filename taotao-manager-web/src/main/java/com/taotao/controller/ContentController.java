package com.taotao.controller;

import com.taotao.content.service.ItemContentService;
import com.taotao.pojo.LayuiResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.ZtreeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.border.TitledBorder;
import java.util.List;

@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ItemContentService itemContentService;
    @RequestMapping("/showContentZtree")
    @ResponseBody
    public List<ZtreeResult> showContentZtree(@RequestParam(value = "id",defaultValue = "0") Long id){
        List<ZtreeResult> result = itemContentService.getZtreeResult(id);
        return result;
    }
    @RequestMapping("/showContentTable")
    @ResponseBody
    public LayuiResult showContentTable(Long categoryId,Integer page,Integer limit){
        LayuiResult result = itemContentService.findContentByCategoryId(categoryId,page,limit);
        return result;
    }
    @RequestMapping("/deleteContentByCategoryId")
    @ResponseBody
    public LayuiResult deleteContentByCategoryId(@RequestBody List<TbContent> tbContents,@RequestParam(value = "page",defaultValue = "1")Integer page,@RequestParam(value = "limit",defaultValue = "10")Integer limit){
        LayuiResult result = itemContentService.deleteContentByCategoryId(tbContents,page,limit);
        return result;
    }

    @RequestMapping("/addContent")
    @ResponseBody
    public TaotaoResult addContent(TbContent tbContent,@RequestParam(value = "page",defaultValue = "1")Integer page,@RequestParam(value = "limit",defaultValue = "10")Integer limit){
        TaotaoResult result = itemContentService.addContent(tbContent,page,limit);
        return result;
    }

}
