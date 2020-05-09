package com.taotao.mapper;


import com.taotao.pojo.TbItemParamGroup;
import com.taotao.pojo.TbItemParamKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbItemParamMapper {
	@Select("SELECT * FROM tbitemparamgroup WHERE itemCatId = #{cId}")
	List<TbItemParamGroup> findTbItemGroupByCId(Long cId);

	int addGroup(TbItemParamGroup group);

	int addGroupKey(@Param("paramKeys") List<TbItemParamKey> paramKeys);
}
