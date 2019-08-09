package com.components.service;

import java.util.Map;

/**
 * 数据处理接口
 * @author Ian.Su
 * @version $Id: ApiDataProcessingService.java, v 0.1 2017/7/11 9:53 Ian.Su Exp $
 */
public interface ApiDataProcessingService {




    /**
     *
     * 根据实际业务情况，对返回数据进行处理
     * @param address 数据请求的地址
     * @param externalParam 请求数据的原始外部参数
     * @param combineParams  合并后的请求参数
     * @param rawData 得到的原始数据
     * @return  处理后的数据
     *
     * */
    Object processing( String address ,
                       Map<String,Object> externalParam ,
                       Map<String,Object> combineParams,
                       Object rawData );

}
