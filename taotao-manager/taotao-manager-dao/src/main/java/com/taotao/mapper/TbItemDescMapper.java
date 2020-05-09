package com.taotao.mapper;



import com.taotao.pojo.TbItemDesc;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface TbItemDescMapper {
    @Insert("INSERT INTO tbitemdesc(itemId, itemDesc, created, updated) VALUE (#{itemId},#{itemDesc},#{created},#{updated})")
    int addItemDesc(TbItemDesc tbItemDesc);
    @Select("SELECT * FROM tbitemdesc WHERE itemId = #{itemId}")
    TbItemDesc findTbItemDescByItemId(Long itemId);
}