package com.taotao.search.controller;

import com.taotao.pojo.SearchItem;
import com.taotao.pojo.SearchResult;
import com.taotao.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping("/search")
    public String showSearch(@RequestParam("q") String query,@RequestParam(value = "page",defaultValue = "1") Integer page,Model model) throws UnsupportedEncodingException {
        String str = new String(query.getBytes("iso-8859-1"),"UTF-8");
        SearchResult result = searchService.findItemSearch(str,page);
        model.addAttribute("query", str);
        model.addAttribute("totalPages", result.getTotalPages());
        List<SearchItem> itemList = result.getItemList();
        model.addAttribute("itemList", itemList);
        model.addAttribute("totalCount",result.getTotalCount());
        model.addAttribute("page", page);
        return "search";
    }
}
