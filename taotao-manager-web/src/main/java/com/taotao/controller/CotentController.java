package com.taotao.controller;

import com.taotao.content.service.ItemContentService;
import com.taotao.pojo.ZtreeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content")
public class CotentController {
    @Autowired
    private ItemContentService itemContentService;
    @RequestMapping("/showContentZtree")
    @ResponseBody
    public List<ZtreeResult> showContentZtree(@RequestParam(value = "id",defaultValue = "0") Long id){
        List<ZtreeResult> result = itemContentService.getZtreeResult(id);
        return result;
    }

}
