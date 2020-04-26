package com.taotao.mapper;



import com.taotao.pojo.TbItemDesc;
import org.apache.ibatis.annotations.Insert;

public interface TbItemDescMapper {
    @Insert("INSERT INTO tbitemdesc(itemId, itemDesc, created, updated) VALUE (#{itemId},#{itemDesc},#{created},#{updated})")
    int addItemDesc(TbItemDesc tbItemDesc);
}