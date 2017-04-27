package com.codezjsos.base.utils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhufang on 2017/2/28.
 */
public class PlaceholderParseUtils {
    private static Pattern pattern=Pattern.compile("\\$\\{([^}]+)\\}");
    public static String  repalce(String context, Map<String,Object> values){
        String context2=context;
        Set<String> set=new HashSet<String>();
        Matcher matcher=pattern.matcher(context);
        while(matcher.find()){
           set.add(matcher.group(1));
        }
        for(String str:set){
            context2=context2.replaceAll("\\$\\{"+str+"\\}",values.get(str).toString());
        }
        return context2;
    }

}
