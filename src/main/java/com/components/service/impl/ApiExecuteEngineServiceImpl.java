package com.components.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.components.dao.CompApiDao;
import com.components.entities.CacheInterfaceEntity;
import com.components.entities.CompApi;
import com.components.exception.ApiException;
import com.components.mapper.CompApiMapper;
import com.components.service.*;
import com.components.util.ErrorMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 本接口返回的数据优先取缓存中的,当缓存存在数据则直接返回.
 *
 * @author Ian.Su
 * @version $Id: ApiExecuteEngineServiceImpl.java, v 0.1 2017/7/6 16:59 Ian.Su Exp $
 */
@SuppressWarnings("ALL")
@Service("defaultApiExecEngine")
public class ApiExecuteEngineServiceImpl implements ApiExecuteEngineService, ApiAddressParamAssemblyInterface {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public final static ExecutorService CACHE_THREAD = Executors.newFixedThreadPool(3);

    @Autowired
    protected CompApiDao compApiDao;

    @Autowired
    protected ApplicationContext applicationContext;

    @Autowired
    protected CompApiMapper compApiMapper;

    @Value("${components.api.re-invok.times:4}")
    private int apiInvokTimes;

    @Override
    public Object execute(String apiId, Map<String, Object> externalParam) throws Exception {

        CompApi compApi = compApiMapper.get(apiId);

        //根据ID获取接口设置
        if (compApi == null) {
            throw new ApiException("ID为 " + apiId + " 的接口配置不存在");
        }

        //参数处理
        Map<String, Object> combineParams = paramCombinAndCheck(compApi, externalParam);
        /*使用接口可以更灵活的切换存储方式*/
        CacheInterfaceEntity cache = loadCacheDataCompCache(compApi, combineParams);
        if (cache != null && new Date().before(cache.getValidDate())) {
            return JSONObject.parseObject(cache.getVal());
        }

        Object msg = null;
        // 网络抖动时重试
        for (int k = 1; k == 1 || k <= apiInvokTimes; k++) {

            if (k != 1) {
                synchronized (Thread.currentThread()) {
                    Thread.currentThread().wait(500 * k);
                }
            }

            Object value = run(k, compApi, externalParam, combineParams);
            if (value != null && !(value instanceof ErrorMsg)) {
                return value;
            }
            msg = value;

        }


        if (cache != null) {
            return JSONObject.parseObject(cache.getVal());
        }

        throw new ApiException(msg.toString());

    }


    public Object run(int counter, CompApi compApi, Map<String, Object> externalParam, Map<String, Object> combineParams) throws Exception {


        Object rawData = null;
        Exception e = null;
        try {
            //执行请求
            rawData = invoking(compApi, combineParams);
            // 返回数据校验和处理
            Object value = dataCheckAndProcessing(compApi, externalParam, combineParams, rawData);
            // 缓存数据
            cacheData(compApi, combineParams, value);

            return value;

        } catch (Exception ex) {
            e = ex;
        }


        return new ErrorMsg(e.getMessage());
    }


    /**
     * 多线程缓存数据
     */
    protected void cacheData(CompApi compApi, Map<String, Object> combineParams, Object val) {


        if (!StringUtils.hasText(compApi.getCacheInterface())) {
            return;
        }


        CACHE_THREAD.execute(new Runnable() {
            @Override
            public void run() {

                ApiCacheService cacheService = applicationContext.getBean(compApi.getCacheInterface(), ApiCacheService.class);

                String key = cacheService.encryptKey(compApi.getId(), combineParams);

                cacheService.set(key, cacheService.validate(), val);

            }
        });


    }


    /**
     * 执行数据请求
     */
    protected Object loadCacheData(CompApi compApi, Map<String, Object> combineParams) {

        if (!StringUtils.hasText(compApi.getCacheInterface())) {
            return null;
        }

        try {

            ApiCacheService cacheService = applicationContext.getBean(compApi.getCacheInterface(), ApiCacheService.class);

            String key = cacheService.encryptKey(compApi.getId(), combineParams.toString());

            return cacheService.get(key);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * 执行数据请求
     */
    protected CacheInterfaceEntity loadCacheDataCompCache(CompApi compApi, Map<String, Object> combineParams) {

        if (!StringUtils.hasText(compApi.getCacheInterface())) {
            return null;
        }

        try {

            ApiCacheService cacheService = applicationContext.getBean(compApi.getCacheInterface(), ApiCacheService.class);

            String key = cacheService.encryptKey(compApi.getId(), combineParams.toString());

            return cacheService.getCompCache(key);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 执行数据请求
     */
    protected Object invoking(CompApi compApi, Map<String, Object> combineParams) throws Exception {

        ApiInvokingService invokingService = applicationContext.getBean(compApi.getInvokingInterface(), ApiInvokingService.class);

        return invokingService.invoking(compApi.getApiUrl(), combineParams);

    }


    /**
     * 返回数据校验和处理
     */
    protected Object dataCheckAndProcessing(CompApi compApi, Map<String, Object> externalParam, Map<String, Object> combineParams, Object value) throws Exception {

        //校验返回值
        if (StringUtils.hasText(compApi.getDataCheckInterface())) {
            ApiDataCheckService dataCheck = applicationContext.getBean(compApi.getDataCheckInterface(), ApiDataCheckService.class);
            dataCheck.checkData(compApi.getApiUrl(), combineParams, value);
        }

        //数据处理
        if (StringUtils.hasText(compApi.getDataProcessingInterface())) {

            ApiDataProcessingService dataProcessing = applicationContext.getBean(compApi.getDataProcessingInterface(), ApiDataProcessingService.class);

            value = dataProcessing.processing(compApi.getApiUrl(), externalParam, combineParams, value);
        }

        return value;

    }


    /**
     * 参数合并和校验
     */
    protected Map<String, Object> paramCombinAndCheck(CompApi compApi, Map<String, Object> externalParam) throws Exception {

        //组合参数
        ApiParamCombineService paraComb = applicationContext.getBean(compApi.getCombineParamInterface(), ApiParamCombineService.class);
        Map<String, Object> combineParams = paraComb.combine(externalParam, compApi.getFixParam(), compApi.getExternalParam());

        //校验参数
        ApiCheckParamService checkParamService = applicationContext.getBean(compApi.getCheckParamInterface(), ApiCheckParamService.class);
        checkParamService.checkParam(combineParams, compApi.getExternalParam());

        return combineParams;

    }


}
