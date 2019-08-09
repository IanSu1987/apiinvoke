package com.components.service;

import java.util.Map;

/**
 * 参数组合接口
 *
 * @author Ian.Su
 * @version $Id: ParamCombineService.java, v 0.1 2017/7/6 14:21 Ian.Su Exp $
 */
public interface ApiParamCombineService {


    /**
     * 合并外部参数与固定参数
     *
     * @param externalParam             外部可变参数
     * @param fixParamJson              json格式的固定参数
     * @param externalParamRestrictJson 外部参数限制条件
     * @return Map<String   ,   Object> 组合后的参数
     */
    Map<String, Object> combine(Map<String, Object> externalParam, String fixParamJson, String externalParamRestrictJson);

}
