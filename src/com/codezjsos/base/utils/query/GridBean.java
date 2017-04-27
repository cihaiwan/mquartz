package com.codezjsos.base.utils.query;

import java.util.List;

import com.codezjsos.base.bean.PageBean;

public class GridBean {

	public PageBean pageBean;
	public List<?> data;
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
	public GridBean(PageBean pageBean, List<?> data) {
		super();
		this.pageBean = pageBean;
		this.data = data;
	}
	
	
	
	
}
