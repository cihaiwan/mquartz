package com.codezjsos.base.utils.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryTerm {

	public static final String EQUELS = "=";
	public static final String IN = "in";
	public static final String LIKE = "like";
	
	public String field;
	public String op;
	public Object value;
	
	
	
	public static Map<String, Object> toMap(List<QueryTerm> queryTerms){
		
		Map<String, Object> m = new HashMap<String, Object>();
		for(QueryTerm q : queryTerms){
			m.put(q.getField(), q.getValue());
		}
		return m;
	}



	public String getField() {
		return field;
	}



	public void setField(String field) {
		this.field = field;
	}



	public String getOp() {
		return op;
	}



	public void setOp(String op) {
		this.op = op;
	}



	public Object getValue() {
		return value;
	}



	public void setValue(Object value) {
		this.value = value;
	}



	public static String getEquels() {
		return EQUELS;
	}



	public QueryTerm(String field, String op, Object value) {
		super();
		this.field = field;
		this.op = op;
		this.value = value;
	}
	
	
}
