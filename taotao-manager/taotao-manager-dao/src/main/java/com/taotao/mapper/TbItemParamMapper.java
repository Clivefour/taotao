package com.taotao.mapper;


import com.taotao.pojo.TbItemParamGroup;
import com.taotao.pojo.TbItemParamKey;
import com.taotao.pojo.TbItemParamValue;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbItemParamMapper {
	@Select("SELECT * FROM tbitemparamgroup WHERE itemCatId = #{cId}")
	List<TbItemParamGroup> findTbItemGroupByCId(Long cId);

	int addGroup(TbItemParamGroup group);

	int addGroupKey(@Param("paramKeys") List<TbItemParamKey> paramKeys);
	@Select("SELECT * FROM tbitemparamkey WHERE groupId = #{id}")
    List<TbItemParamKey> findTbItemParamKeyByGroupId(Integer id);
	@Insert("INSERT INTO tbitemparamvalue(itemId, paramId, paramValue) VALUE (#{itemId},#{paramId},#{paramValue})")
	int addGroupValue(TbItemParamValue tbItemParamValue);

	List<TbItemParamGroup> findTbItemGroupByItemId(@Param("itemId") Long itemId);
}
