package com.shici.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * FileName: WebUtils
 * Description:
 * Author: CSH
 * Date: 2021/1/2 20:12
 * Version: 1.0
 */
public class WebUtils {
    //把所有请求都注入到user对象中
    public static <T> T copyParamToBean(Map value, T bean){

        try {
            System.out.println("注入之前：" + bean);

            BeanUtils.populate(bean,value);
            System.out.println("注入之后：" + bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static int parseInt(String strInt, int defaultValue){
        try {
            return Integer.parseInt(strInt);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
