package com.codezjsos.base.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhufang on 2017/2/28.
 */
public class MessageFactory {

    public Map<String,Object> build(){
        return map;
    }
    private Map<String,Object> map=new HashMap<String,Object>();

    public MessageFactory put(String key,Object value){
        map.put(key,value);
        return  this;
    }
    public MessageFactory putAll(Map<String,Object> value){
    	map.putAll(value);
    	return  this;
    }
    private MessageFactory(){}
    public static MessageFactory create(){
        return new MessageFactory();
    }
}
