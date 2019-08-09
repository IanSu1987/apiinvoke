package com.components.service;

import java.util.Map;

/**
 * @author Ian.Su
 * @version $Id: ApiDataapiDataCheckService.java, v 0.1 2017/7/10 15:53 Ian.Su Exp $
 */
public interface ApiDataCheckService {


    /**
     * 返回值检测,返回值监测不通过则抛出异常
     * @param address 接口地址
     * @param params 请求接口的参数
     * @param value 接口获取到的返回值
     *
     * */
    boolean checkData(String address , Map<String,Object> params , Object value) throws Exception;

}
