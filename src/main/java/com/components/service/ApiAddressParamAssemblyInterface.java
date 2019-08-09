package com.components.service;

import com.alibaba.fastjson.JSONObject;
import com.components.util.ErrorMsg;

import java.util.Map;

/**
 *
 * 组合请求详细信息
 *
 * @author Ian.Su
 * @version $Id: ApiAddressParamAssemblyInterface.java, v 0.1 2017/7/10 17:32 Ian.Su Exp $
 */
public interface ApiAddressParamAssemblyInterface {




    /**
     *
     * 将 请求地址、参数与返回值 组合成字符串
     * @param address 请求地址
     * @param params  请求参数
     * @param val 返回值
     * @return String
     *
     * */
    default String assembly(String address , Map<String,Object> params,Object val){

        StringBuilder msg = new StringBuilder(assembly( address ,  params));

        msg.append(System.lineSeparator());
        msg.append("返回值为:").append(JSONObject.toJSON(val));
        msg.append(System.lineSeparator());

        return msg.toString();

    }



    /**
     *
     * 将 请求地址、参数与返回值 组合成字符串
     * @param address 请求地址
     * @param params  请求参数
     * @param val 返回值
     * @return String
     *
     * */
    default ErrorMsg assemblyErrorMsg(String address , Map<String,Object> params, Object val,Exception e){

        return new ErrorMsg( assembly( address , params, val)  +  System.lineSeparator() + " 异常信息： " + e.getMessage() );

    }





    /**
     * 将 请求地址与参数 组合成字符串
     * @param address 请求地址
     * @param params  请求参数
     * @return String
     * */
    default String assembly(String address , Map<String,Object> params){

        StringBuilder msg = new StringBuilder();

        if (params!=null){
            params.entrySet().iterator().forEachRemaining(entry->{
                msg.append(msg.length()==0?"?":"&").append(entry.getKey()).append("=").append(entry.getValue());
            });
        }

        msg.insert(0,address);
        msg.insert(0,System.lineSeparator());
        msg.insert(0,"请求第三方接口,地址:");

        return msg.toString();

    }




    /**
     * 将 请求地址与参数 组合成字符串
     * @param address 请求地址
     * @param params  请求参数
     * @return String
     * */
    default String assembly(String address , Map<String,Object> params,Exception e){

        StringBuilder msg = new StringBuilder();

        if (params!=null){
            params.entrySet().iterator().forEachRemaining(entry->{
                msg.append(msg.length()==0?"?":"&").append(entry.getKey()).append("=").append(entry.getValue());
            });
        }

        msg.insert(0,address);
        msg.insert(0,System.lineSeparator());
        msg.insert(0,"请求第三方接口,地址:");

        msg.append(System.lineSeparator());
        msg.append("异常信息:");
        msg.append(e.getCause());

        return msg.toString();

    }





}
