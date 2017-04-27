package com.codezjsos.base.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.engine.spi.Mapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codezjsos.base.ICommonService;
import com.codezjsos.base.bean.PageBean;
import com.codezjsos.base.utils.JsonUtils;
import com.codezjsos.base.utils.XmlUtils;
import com.codezjsos.base.utils.query.QueryTerm;
import com.codezjsos.base.utils.query.RemoveBean;
import com.codezjsos.base.utils.query.ResultBean;
import com.codezjsos.base.utils.query.SaveBean;
import com.codezjsos.base.utils.query.UpdateBean;

/**
 * 
 * 
 * core service
 * 
 * @date 2017年3月25日18:26:10
 * @author shar
 *
 */
public class CommonService extends BaseServiceImpl implements ICommonService{

	public static Logger logger = LoggerFactory.getLogger(CommonService.class);

	

	

	//<addition id="handleUnitUnid" type=" and p.handleUnitUnid =:handleUnitUnid "/>
	//<addition id="applyCode" type="  and p.applyCode like :applyCode " conver="%placeholder%"/>
	public String createWhere(List<Map<String,String>> list2, Map<String, Object> params){

		String hql="";
		for(Map<String,String> map4:list2){
			Object value = params.get(map4.get("id"));
			if(value==null){
				continue;
			}
			if(params.get(map4.get("id")) instanceof String){
				if(StringUtils.isEmpty(params.get(map4.get("id")).toString())){
					continue;
				}
			}else if(params.get(map4.get("id")) instanceof Collection){
				Collection<?> coll=(Collection<?>) params.get(map4.get("id"));
				if(coll==null||coll.size()==0){
					continue;
				}
			}else if(params.get(map4.get("id")) instanceof Object[]){
				Object[] coll=(Object[]) params.get(map4.get("id"));
				if(coll==null||coll.length==0){
					continue;
				}
			}
			hql+=map4.get("type");
			if(map4.containsKey("conver")){
				String placeholder=map4.get("conver").replaceAll("placeholder",params.get(map4.get("id").toString()).toString());
				params.put(map4.get("id").toString(),placeholder);
			}else if(map4.containsKey("contain")&&map4.get("contain").equals("between")){
				Object v=params.get(map4.get("id").toString());
				List<Object> l=null;
				if(v instanceof Object[]){
					Object[] vv=(Object[])v;
					l=Arrays.asList(vv);
				}else if(v instanceof Collection){
					Collection<?> vv=(Collection<?>)v;
					l=new ArrayList<Object>(vv);
				}
				hql+=" between " +" :between"+map4.get("id").toString() +" and "+" :and"+map4.get("id").toString();
				params.put("between"+map4.get("id").toString(),l.get(0));
				params.put("and"+map4.get("id").toString(),l.get(1));
			}
		}
		return hql;
	}
	public String createNotWhere(Map<String,Object> map,String attr){
		String hql=" ";
		if(map.containsKey(attr)){
			hql+=map.get(attr);
		}
		return hql;
	}


	public String packageSQL(String xml, String sqlId, Map<String, Object> params) throws Exception{
		String hql=XmlUtils.readText(xml, sqlId);
		String[] id=new ArrayList<String>(params.keySet()).toArray(new String[]{});
		List<Map<String, String>> list2=XmlUtils.readElements(xml, id); 
		StringBuffer buffer=new StringBuffer();
		buffer.append(hql);
		buffer.append(createWhere(list2,params));
		Map<String,Object> attrs=XmlUtils.readAttrForMap(xml, sqlId);
		buffer.append(createNotWhere(attrs,"group"));
		buffer.append(createNotWhere(attrs,"order"));
		return buffer.toString();
	}
	



}
