package com.taotao.mapper;


import com.taotao.pojo.TbContentCategory;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbContentCategoryMapper {
    @Select("SELECT * FROM tbcontentcategory WHERE parentId = #{id}")
    List<TbContentCategory> findContentByParentId(Long id);
}