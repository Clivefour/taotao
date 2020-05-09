package com.taotao.pojo;

public class TbItemParamKey {
	private Integer id;
	private String paramName;
	private Integer groupId;
	//建立多对一的关系
	private TbItemParamGroup paramGroup;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public TbItemParamGroup getParamGroup() {
		return paramGroup;
	}
	public void setParamGroup(TbItemParamGroup paramGroup) {
		this.paramGroup = paramGroup;
	}
	@Override
	public String toString() {
		return "TbItemParamKey [id=" + id + ", paramName=" + paramName + ", groupId=" + groupId + "]";
	}
}
