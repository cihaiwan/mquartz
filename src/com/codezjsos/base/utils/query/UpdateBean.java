package com.codezjsos.base.utils.query;

public class UpdateBean {

	public String tableName;///实体类名
	public String json;
	public String updateColumn;
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
	public String getUpdateColumn() {
		return updateColumn;
	}
	public void setUpdateColumn(String updateColumn) {
		this.updateColumn = updateColumn;
	}
	public UpdateBean(String tableName, String json, String updateColumn) {
		super();
		this.tableName = tableName;
		this.json = json;
		this.updateColumn = updateColumn;
	}
	
	
}
