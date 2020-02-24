package com.lovo.config;

public class StringUtil {
    /**
     * 判断字符串是否为空，如果不为空返回true否则返回false
     * @param str
     * @return
     */
    public static boolean blString(String str){
        if(null!=str&&!"".equals(str)){
            return true;
        }
        return false;
    }

}
