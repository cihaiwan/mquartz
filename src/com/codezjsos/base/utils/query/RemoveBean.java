package com.codezjsos.base.utils.query;

public class RemoveBean extends DBBean{

	public String tableName;
	public String ids;
	public static final String SQL = "s";
	public static final String HQL = "h";
	public String type = "h";//hql //sql
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public RemoveBean(String tableName, String ids) {
		super();
		this.tableName = tableName;
		this.ids = ids;
	}
	
	
}
