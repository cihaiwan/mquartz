package com.codezjsos.base.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Created by zhufang on 2017/2/27.
 */
public class XmlUtils {
    public XmlUtils() {
        super();
    }
    private static Document instance(String filename) throws DocumentException {
        SAXReader sr=new SAXReader();
        return sr.read(XmlUtils.class.getClassLoader().getResourceAsStream(filename));
    }

    private static Element getById(String filename,String id) throws DocumentException {
        Element element= (Element) instance(filename).selectSingleNode("//*[@id='"+id+"']");
        return element;
    }
    public static String readValueById(String filename,String id,String attrName) throws DocumentException {
            return getById(filename,id).attributeValue(attrName);
    }

    public static String readVauleRefAttr(String filename,String id,String attrName) throws DocumentException {
        String refid=readValueById(filename,id,"ref");
        return readValueById(filename,refid,attrName);
    }

    private static Map<String,Object> readFieldMap(Element element ){
        Map<String,Object> map=new HashMap<String,Object>();
        for(Object obj:element.attributes()){
            Attribute attr= (Attribute) obj;
            if("id".equals(attr.getName()))continue;
            map.put(attr.getName(),attr.getValue());
        }
        return map;
    }
    /**
     * 
     * 当前节点的所有属性
     * @author shar
     *
     */
    public static Map<String,Object> readAttrForMap(String filename,String id) throws DocumentException {

        return readFieldMap(getById(filename,id));
    }

    private static List<Map<String,Object>> readElements(List<Element> list){

        List<Map<String,Object>> fields=new ArrayList<Map<String, Object>>();
        for(Element e:list){
            fields.add(readFieldMap(e));
        }
        return fields;
    }
    public static List<Map<String,Object>> readElements(String filename,String id) throws DocumentException {
        Element element=getById(filename, id);
        List<Element> list=element.elements();
        return readElements(list);
    }
    public static List<Map<String,String>> readElements(String filename,String[] id) throws DocumentException {
    	List<Map<String,String>> map=new ArrayList<Map<String,String>>();
		Element element=instance(filename).getRootElement();
		for(String  i:id){
			Element element2=((Element)element.selectSingleNode("//*[@id='"+i+"']"));
			if(element2==null){
				continue;
			}
			Map<String,String> map2=new HashMap<String, String>();
			for(Object attribute:element2.attributes()){
				Attribute value=(Attribute) attribute;
				map2.put(value.getName(), value.getValue());
			}
			map.add( map2);
		}
		return map;
    }
    /**
     * 
     * @date 2017年3月16日18:03:42
     * @author shar
     *
     */
    public static List<Map<String,String>> readElements(String filename, String node, String[] id) throws DocumentException {
    	List<Map<String,String>> map=new ArrayList<Map<String,String>>();
		Element element=(Element) instance(filename).getRootElement().selectSingleNode(node);
		for(String  i:id){
			Element element2=((Element)element.selectSingleNode("//*[@id='"+i+"']"));
			if(element2==null){
				continue;
			}
			Map<String,String> map2=new HashMap<String, String>();
			for(Object attribute:element2.attributes()){
				Attribute value=(Attribute) attribute;
				map2.put(value.getName(), value.getValue());
			}
			map.add( map2);
		}
		return map;
    }
    public static List<Map<String,Object>> readElements(String filename,String id,String tag) throws DocumentException {
        Element element=getById(filename, id);
        List<Element> list=element.selectNodes(tag);
        return readElements(list);
    }

    public static List<Map<String,Object>> readElementsByTag(String filename,String tag) throws DocumentException {
        Element element= (Element) instance(filename).selectSingleNode("//"+tag);
        return readElements(element.elements());
    }

    public static Object attrConverObject (Class<?> cls,Map<String,Object> values) throws Exception {
        Object obj=cls.newInstance();
        Field[] fields=cls.getFields();
        for(Field f:fields){
            String fieldname=f.getName();
            if(values.containsKey(fieldname)){
                BeanUtils.setProperty(obj,fieldname,values.get(fieldname));
            }
        }
        return obj;
    }
    /**
     * 
     * 读取所有select这种节点的内容
     * @author shar
     *
     */
    public static List<String> readTexts(String filename,String select) throws DocumentException {
        List list = instance(filename).selectNodes(select);
        List<String> list2=new ArrayList<String>();
        for(Object obj:list){
            Element e=(Element)obj;
            list2.add(e.getTextTrim());
        }
        return list2;
    }
    public static String  readText(String filename,String id) throws DocumentException {
        return getById(filename,id).getTextTrim();
    }

    public static String  readTextForSelect(String filename,String select) throws DocumentException {
        return((Element)instance(filename).selectSingleNode(select)).getTextTrim();
    }
}
