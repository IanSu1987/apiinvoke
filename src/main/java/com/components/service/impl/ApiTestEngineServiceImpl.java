package com.components.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.components.dao.CompApiDao;
import com.components.entities.CompApi;
import com.components.exception.ApiException;
import com.components.mapper.CompApiMapper;
import com.components.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 本接口返回的数据优先取缓存中的,当缓存存在数据则直接返回.
 * @author Ian.Su
 * @version $Id: ApiExecuteEngineServiceImpl.java, v 0.1 2017/7/6 16:59 Ian.Su Exp $
 */
@SuppressWarnings("ALL")
@Service("testApiExecEngine")
public class ApiTestEngineServiceImpl extends ApiExecuteEngineServiceImpl {




    @Override
    public Object execute(String apiId , Map<String, Object> externalParam) throws Exception {

        if(externalParam == null){
            externalParam = new HashMap<>();
        }

        // 根据ID获取接口设置
        CompApi compApi = compApiMapper.get(apiId);

        if(compApi == null) throw new ApiException("ID为 "+ apiId + " 的接口配置不存在");

        // 外部参数覆盖测试参数
        externalParam = externalOverTest( externalParam , compApi.getTestParam() );

        // 参数处理
        Map<String,Object> combineParams = paramCombinAndCheck(compApi , externalParam);

        // 执行请求
        Object value = invoking(compApi, combineParams);

        // 返回数据校验和处理
        value = dataCheckAndProcessing( compApi ,  externalParam, combineParams ,  value );

        // 缓存数据
        cacheData( compApi , combineParams , value );

        return value;

    }



    /**
     * 外部参数覆盖测试参数
     * */
    protected Map<String,Object> externalOverTest(Map<String,Object> externalParam , String testParam ){

        if( !StringUtils.hasText(testParam) ){
            return externalParam;
        }

        Map<String,Object> testMap = JSONObject.parseObject(testParam,Map.class);

        testMap.putAll(externalParam);

        return testMap;
    }



}
