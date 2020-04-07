package com.logistics.cloud.tools;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexTools {

    /**
     * @param str 被匹配的字符串
     * @param pattern 正则表达式
     * @return  是否匹配成功
     */
    public static boolean regCheck(String str , Pattern pattern){
        if (StringUtils.isEmpty(str)){
            return false;
        }
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
