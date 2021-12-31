package com.atliu.utils;

import com.atliu.pojo.User;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
public class WebUtils {
    /**
     *把map中的值注入到对应的JavaBean属性中
     * @param value
     * @param bean
     * Dao层
     * Service层
     * Web层
     */
    public static <T> T copyParamToBean(Map value, T bean){
        try {
            System.out.println("注入之前" + bean);
            /**
             * 把所有的请求参数都注入都bean实体类里边
             */
            BeanUtils.populate(bean,value);
            System.out.println("注入之后" + bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符串转换成为Int类型的数据
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt,int defaultValue){

            if(strInt != null && !strInt.equals("")){
                try {
                    return Integer.parseInt(strInt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        return defaultValue;
    }
}
