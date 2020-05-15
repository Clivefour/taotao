package com.taotao.order.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;
import com.taotao.pojo.TbUser;

import java.util.List;

public interface OrderService {
    /**
     * 添加订单到数据库中
     * @param orderItemList 订单项信息
     * @param tbOrderShipping 用户信息
     * @param payment 总金额
     * @param paymentType 支付类型
     * @param tbUser 用户信息
     * @return data里面是 订单号
     */
    TaotaoResult addOrder(List<TbOrderItem> orderItemList, TbOrderShipping tbOrderShipping, String payment, Integer paymentType, TbUser tbUser);
}
