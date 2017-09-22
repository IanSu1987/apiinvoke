package com.components.service;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;

import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * 缓存接口
 *
 * @author Ian.Su
 * @version $Id: ApiCacheService.java, v 0.1 2017/7/11 11:20 Ian.Su Exp $
 */
public interface ApiCacheService {


    /**
     * 根据Key获得缓存中的数据
     * @param key 缓存唯一值
     * @return Object 缓存中的数据
     *
     * */
    Object get(String key);



    /**
     *  根据Key获得缓存中的数据
     *
     * */
    void set(String key, Date validate, Object value);



    /**
     * 生成有效截止时间,默认为当天23:59:59
     * */
    default Date validate(){

       Calendar today = Calendar.getInstance();

       today.set(Calendar.HOUR_OF_DAY,23);
       today.set(Calendar.MINUTE,59);
       today.set(Calendar.SECOND,59);

       return today.getTime();

    }



    /**
     * 生成唯一key值
     * @param preffix  key值前缀
     * @param content 要加密的数据
     * @param contents 可以是多个content
     * @return  String 将数据组合后进行加密
     * */
    default String encryptKey(String preffix , @NotNull Object content , Object ... contents){

        StringBuilder val = new StringBuilder(content.toString());

        if(contents != null){
            for (Object o : contents) {
                val.append("_").append(null == o ? "" : o.toString());
            }
        }

        val.insert(0 ,"-");
        val.insert(0 ,preffix);

        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        String pwd = encoder.encodePassword( val.toString() , preffix );

        return preffix + "_" + pwd;

    }

}
