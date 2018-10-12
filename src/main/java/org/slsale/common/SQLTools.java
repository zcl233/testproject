package org.slsale.common;

/**
 * mybatis 防止sql注入工具
 */
public class SQLTools {
    public static String transfer(String keyword){
        if(keyword.contains("%")||keyword.contains("_")){
            keyword=keyword.replaceAll("\\\\","\\\\\\\\")
                    .replaceAll("\\%","\\\\%")
                    .replaceAll("\\_","\\\\_");
        }
        return keyword;
    }

}
