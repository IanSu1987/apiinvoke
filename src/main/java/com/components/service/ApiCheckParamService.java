package com.components.service;

import com.components.exception.ApiException;

import java.util.Map;

/**
 * @author Ian.Su
 * @version $Id: ApiCheckParamService.java, v 0.1 2017/7/10 11:20 Ian.Su Exp $
 */
public interface ApiCheckParamService {

    /**
     *
     * 参数校验
     * @param sourceParam 参数源
     * @param includeCheckJson 必须包含的参数
     *
     *
     * */
    Map<String,Object> checkParam( Map<String,Object> sourceParam, String includeCheckJson ) throws ApiException;

}
