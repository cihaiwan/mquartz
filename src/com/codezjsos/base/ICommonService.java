package com.codezjsos.base;

import java.util.Map;

public interface ICommonService extends IBaseService{
	
	public String packageSQL(String xml, String sqlId, Map<String, Object> params) throws Exception;
}
