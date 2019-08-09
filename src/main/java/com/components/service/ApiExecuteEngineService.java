package com.components.service;

import java.util.Map;

/**
 *
 * API调用执行引擎
 *
 * @author Ian.Su
 * @version $Id: ApiExecuteEngine.java, v 0.1 2017/7/6 16:33 Ian.Su Exp $
 */
public interface ApiExecuteEngineService {


    /**
     * 执行api调用全过程
     * @param apiId apiId
     * @param externalParam 外部参数
     *
     * */
    Object execute(String apiId , Map<String,Object> externalParam)  throws Exception ;

}
