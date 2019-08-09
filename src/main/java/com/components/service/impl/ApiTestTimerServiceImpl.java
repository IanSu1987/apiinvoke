package com.components.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.components.dao.CompApiDao;
import com.components.dao.CompCacheDao;
import com.components.entities.CompApi;
import com.components.mapper.CompApiMapper;
import com.components.service.ApiExecuteEngineService;
import com.components.service.ApiTestTimerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Ian.Su
 * @version $Id: ApiTestTimerServiceImpl.java, v 0.1 2017/7/17 11:31 Ian.Su Exp $
 */
@SuppressWarnings("ALL")
@Component
public class ApiTestTimerServiceImpl implements ApiTestTimerService {

    private static Logger LOGGER = LoggerFactory.getLogger(ApiTestTimerServiceImpl.class);

    private ScheduledExecutorService scheduledThreadPool = Executors.newSingleThreadScheduledExecutor();

    @Resource(name = "testApiExecEngine")
    private ApiExecuteEngineService testEngineService;

    @Autowired
    private CompApiDao apiDao;

    @PostConstruct
    @Override
    public void timerExec() {

        Iterable<CompApi> it = apiDao.findAll();
        it.forEach(api -> {

            if (api.getTimerPoll() == null || api.getTimerPoll() < 1) {
                return;
            }

            scheduledThreadPool.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    try {
                        LOGGER.debug("执行接口测试：{}", JSONObject.toJSONString(api));
                        testEngineService.execute(api.getId(), null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 10, api.getTimerPoll(), TimeUnit.SECONDS);
        });


    }


}
