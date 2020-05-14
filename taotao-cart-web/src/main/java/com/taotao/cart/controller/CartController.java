package com.taotao.cart.controller;

import com.taotao.constant.RedisConstant;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/add/{itemId}")
    public String cartSuccess(@PathVariable Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response){
        List<TbItem> cartList = getCartList(request);
        // 2、判断商品在商品列表中是否存在。
        boolean hasItem = false;
        for (TbItem item:cartList) {
            //新添加的商品已经在cookie中了
            if(item.getId() == itemId.longValue()){
                //在原来的基础之上 加上新增加的
                item.setNum(item.getNum()+num);
                hasItem = true;
            }
        }
        if(!hasItem){
            //第一次添加商品到购物车的逻辑
            TbItem tbItem = itemService.findTbItemById(itemId);
            String image = tbItem.getImage();
            if(StringUtils.isNotBlank(image)){
                String[] split = image.split("http");
                tbItem.setImage("http"+split[1]);
            }
            //设置购买商品数量
            tbItem.setNum(num);
            cartList.add(tbItem);
        }
        //list集合里面一定有商品数据了
        CookieUtils.setCookie(request, response, RedisConstant.TT_CART, JsonUtils.objectToJson(cartList), RedisConstant.CART_EXPIRE, true);
        //跳转页面
        return "cartSuccess";
    }
    private List<TbItem> getCartList(HttpServletRequest request) {
        //取购物车列表
        String json = CookieUtils.getCookieValue(request, RedisConstant.TT_CART, true);
        //判断json是否为null
        if (StringUtils.isNotBlank(json)) {
            //把json转换成商品列表返回
            List<TbItem> list = JsonUtils.jsonToList(json, TbItem.class);
            return list;
        }
        return new ArrayList<>();
    }

    @RequestMapping("/cart")
    public String showCart(Model model,HttpServletRequest request){
        List<TbItem> cartList = getCartList(request);
        model.addAttribute("cartList",cartList);
        int sum = 0;
        for (TbItem item:cartList) {
            sum+=item.getNum();
        }
        model.addAttribute("sum",sum);
        return "cart";
    }
    //购物车商品数量修改方法
    @RequestMapping("/update/num/{itemId}/{num}")
    @ResponseBody
    public TaotaoResult updateNum(@PathVariable Long itemId, @PathVariable Integer num,
                                  HttpServletRequest request, HttpServletResponse response,Model model) {
        // 1、接收两个参数
        // 2、从cookie中取商品列表
        List<TbItem> cartList = getCartList(request);
        // 3、遍历商品列表找到对应商品
        for (TbItem tbItem : cartList) {
            if (tbItem.getId() == itemId.longValue()) {
                // 4、更新商品数量
                tbItem.setNum(num);
            }
        }
        model.addAttribute("cartList",cartList);
        int sum = 0;
        for (TbItem item:cartList) {
            sum+=item.getNum();
        }
        model.addAttribute("sum",sum);
        // 5、把商品列表写入cookie。
        CookieUtils.setCookie(request, response, RedisConstant.TT_CART, JsonUtils.objectToJson(cartList), RedisConstant.CART_EXPIRE, true);
        // 6、响应TaoTaoResult。Json数据。
        return TaotaoResult.ok();
    }
    @RequestMapping("/delete/{itemId}")
    public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response) {
        List<TbItem> list = getCartList(request);
        //为什么用 普通for循环 ？ 增强for循环 底层是 迭代器 迭代器在循环的时候 不能修改集合里面的数据
        for (int i = 0;i<list.size();i++) {
            TbItem tbItem = list.get(i);
            if(tbItem.getId()==itemId.longValue()){
                list.remove(tbItem);
                break;
            }
        }
        //集合里面一定有值了 并且把他存入我们的cookie里面
        CookieUtils.setCookie(request, response, RedisConstant.TT_CART, JsonUtils.objectToJson(list), RedisConstant.CART_EXPIRE, true);
        return "redirect:/cart/cart.html";
    }



}
