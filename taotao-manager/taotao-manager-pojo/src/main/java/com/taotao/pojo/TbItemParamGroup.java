package com.taotao.pojo;

import java.util.List;

public class TbItemParamGroup {
	private Integer id;
	private String groupName;
	private Long itemCatId;
	//建立一对多关系
	private List<TbItemParamKey> paramKeys;
	
	public List<TbItemParamKey> getParamKeys() {
		return paramKeys;
	}
	public void setParamKeys(List<TbItemParamKey> paramKeys) {
		this.paramKeys = paramKeys;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Long getItemCatId() {
		return itemCatId;
	}
	public void setItemCatId(Long itemCatId) {
		this.itemCatId = itemCatId;
	}
	@Override
	public String toString() {
		return "TbItemParamGroup [id=" + id + ", groupName=" + groupName + ", itemCatId=" + itemCatId + "]";
	}
	
}
