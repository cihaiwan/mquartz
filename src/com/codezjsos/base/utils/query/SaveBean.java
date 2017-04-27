package com.codezjsos.base.utils.query;

public class SaveBean {

	
	public String tableName;//其实是实体类名
	public String json;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public SaveBean(String tableName, String json) {
		super();
		this.tableName = tableName;
		this.json = json;
	}
	
}
