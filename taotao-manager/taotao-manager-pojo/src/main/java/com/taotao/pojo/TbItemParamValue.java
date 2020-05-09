package com.taotao.pojo;

public class TbItemParamValue {
	private Long itemId;
	private Integer paramId;
	private String paramValue;
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Integer getParamId() {
		return paramId;
	}
	public void setParamId(Integer paramId) {
		this.paramId = paramId;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	@Override
	public String toString() {
		return "TbItemParamValue [itemId=" + itemId + ", paramId=" + paramId + ", paramValue=" + paramValue + "]";
	}
	
}
