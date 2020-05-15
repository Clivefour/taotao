package com.taotao.order.service.impl;

import com.taotao.constant.RedisConstant;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.order.service.JedisClient;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TbOrderMapper tbOrderMapper;
    @Autowired
    private JedisClient jedisClient;

    @Override
    public TaotaoResult addOrder(List<TbOrderItem> orderItemList, TbOrderShipping tbOrderShipping, String payment, Integer paymentType, TbUser tbUser) {
        TbOrder tbOrder = new TbOrder();
        if(!jedisClient.exists(RedisConstant.ORDER_GEN_KEY)){
            //这里是设置订单号的 订单号我放在 redis中  key(ORDER_GEN_KEY) value(100544)
            //  111111 - 100544 = 今天的订单总数
            jedisClient.set(RedisConstant.ORDER_GEN_KEY,RedisConstant.ORDER_ID_BEGIN);
        }
        //传进来一个key 吧这个key所对应的value 进行一个自增长+1的操作
        String orderId = jedisClient.incr(RedisConstant.ORDER_GEN_KEY).toString();
        //订单号了  orderId - 100544 = 今天的订单总数 100545 - 100544 = 1
        tbOrder.setOrderId(orderId);
        //邮费
        tbOrder.setPostFee("0");
        //1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
        tbOrder.setStatus(1);
        Date date = new Date();
        tbOrder.setCreateTime(date);
        tbOrder.setUpdateTime(date);
        //设置用户id
        tbOrder.setUserId(tbUser.getId());
        //设置用户昵称
        tbOrder.setBuyerNick(tbUser.getUserName());
        //添加order表
        int i = tbOrderMapper.addOrder(tbOrder);
        if(i<=0){
            return TaotaoResult.build(500,"生成订单失败");
        }

        //添加订单项表
        for (TbOrderItem orderItem:orderItemList) {
            //20190816 自增长 + 1
            String orderItemId = jedisClient.incr(RedisConstant.ORDER_ITEM_ID_GEN_KEY).toString();
            orderItem.setOrderId(orderId);
            orderItem.setId(orderItemId);
            int j = tbOrderMapper.addOrderItem(orderItem);
            if(j<=0){
                return TaotaoResult.build(500,"生成订单失败");
            }
        }

        //设置订单号
        tbOrderShipping.setOrderId(orderId);
        tbOrderShipping.setCreated(date);
        tbOrderShipping.setUpdated(date);

        int x = tbOrderMapper.addOrderShipping(tbOrderShipping);
        if(x<=0){
            return TaotaoResult.build(500,"生成订单失败");
        }

        return TaotaoResult.ok(orderId);
    }
}
