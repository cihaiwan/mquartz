package com.codezjsos.base.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by zhufang on 2017/2/28.
 */
public class JsonUtils {
    private static Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public static String toJson(Object obj){
        return gson.toJson(obj);
    }

    
    public static Map<String,Object> fromObject(Object dto){
       return gson.fromJson(toJson(dto),Map.class);
    }
    
    public static Map<String,Object> fromJson(String json){
       return gson.fromJson(json,Map.class);
    }
    
    /** 把JSON字符转换为对象 */
	public static <T> T formObject(Type type, String json) {
		return gson.fromJson(json, type);
	}
	
	public static <T> T formObject(Class clazz, String json){
		return (T) gson.fromJson(json, clazz);
	}
}
