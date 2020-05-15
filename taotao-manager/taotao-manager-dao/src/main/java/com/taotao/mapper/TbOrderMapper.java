package com.taotao.mapper;


import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;
import org.apache.ibatis.annotations.Insert;

public interface TbOrderMapper {
    @Insert("INSERT INTO tborder(orderId, payment, paymentType, postFee, status, createTime, updateTime, userId, buyerNick) VALUE (#{orderId},#{payment},#{paymentType},#{postFee},#{status},#{createTime},#{updateTime},#{userId},#{buyerNick})")
    int addOrder(TbOrder tbOrder);
    @Insert("INSERT INTO tborderitem(id, itemId, orderId, num, title, price, totalFee, picPath) VALUE (#{id},#{itemId},#{orderId},#{num},#{title},#{price},#{totalFee},#{picPath})")
    int addOrderItem(TbOrderItem orderItem);
    @Insert("INSERT INTO tbordershipping (orderId, receiverName, receiverPhone, receiverMobile, receiverState, receiverCity, receiverDistrict, receiverAddress, receiverZip, created, updated) VALUE (#{orderId},#{receiverName},#{receiverPhone},#{receiverMobile},#{receiverState},#{receiverCity},#{receiverDistrict},#{receiverAddress},#{receiverZip},#{created},#{updated})")
    int addOrderShipping(TbOrderShipping tbOrderShipping);
}