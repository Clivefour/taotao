package com.taotao.mapper;


import com.taotao.pojo.SearchItem;
import com.taotao.pojo.TbItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface TbItemMapper {
    // 查询数据库tbitem表 中 根据商品id查询商品信息
    @Select("SELECT * FROM tbitem WHERE id = #{id}")
    TbItem findTbItemById(Long itemId);
    // 查询数据库tbitem表 中的总记录条数
    @Select("SELECT count(*) FROM tbitem")
    int findTbItemByCount();
    @Select("SELECT * FROM tbitem LIMIT #{index},#{pageSize}")
    List<TbItem> findTbItemByPage(@Param("index") int index, @Param("pageSize") int pageSize);
    int updateItemByIds(@Param("ids") List<Long> ids, @Param("type")int type, @Param("date")Date date);

    int findTbItemByLikeConut(@Param("title") String title, @Param("priceMin")Integer priceMin, @Param("priceMax")Integer priceMax, @Param("cId")Long cId);

    List<TbItem> findTbItemByLike(@Param("title")String title, @Param("priceMin")Integer priceMin, @Param("priceMax")Integer priceMax, @Param("cId")Long cId, @Param("page")int page, @Param("limit")Integer limit);
    @Insert("INSERT INTO tbitem(id, title, sellPoint, price, num, barcode, image, cId, created, updated) VALUE (#{id},#{title},#{sellPoint},#{price},#{num},#{barcode},#{image},#{cId},#{created},#{updated})")
    int addItem(TbItem tbItem);

    List<SearchItem> findSearchItemAll();

    SearchItem findSearchItemById(@Param("id") Long id);
}