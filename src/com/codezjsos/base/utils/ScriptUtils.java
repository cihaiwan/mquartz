package com.codezjsos.base.utils;

import com.google.gson.GsonBuilder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by zhufang on 2017/2/28.
 */
public class ScriptUtils {
    public static ScriptEngineManager scriptEngineManager=new ScriptEngineManager();
    public static ScriptEngine engine=scriptEngineManager.getEngineByName("JavaScript");

    public static Object  invoke(String script,Object obj) throws Exception {
        String jsonstr=JsonUtils.toJson(obj);
        String functionname=script.replaceAll(".*function([^(]+).*","$1").trim();
        engine.eval(script);
        Invocable invocable=(Invocable) engine;

        return invocable.invokeFunction(functionname,jsonstr);
    }

    public static void main(String[] args) throws Exception {

      //Object obj=  ScriptUtils.invoke("function conver(jsonstr){ var jsonobj=JSON.parse(jsonstr); var v=jsonobj['msg']; if(v==null){ return \"{\\\"success\\\":false,\\\"msg\\\":\\\"msg字段未录入\\\"}\"; } var vv=v.match(/\\d+/)[0]; var vvv=jsonobj['date']; if(vvv==null){ return \"{\\\"success\\\":false,\\\"msg\\\":\\\"date字段未录入\\\"}\"; } var dates=vvv.split(/[-\\s]+/g); var year=parseInt(dates[0])+parseInt(vv); var month=dates[1]; var day=dates[2]; return \"{\\success\\\":true,\\\"msg\\\":\\\"转换成功\\\",\\\"data\\\":\\\"\"+year+\"-\"+month+\"-\"+day+\"\\\"}\"; }",MessageFactory.create().put("date","2013-01-01").put("msg","有效期2年").build());
        //System.out.println(obj);
    }
}
