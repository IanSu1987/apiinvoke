package com.components.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.components.service.ApiDataProcessingService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Ian.Su
 * @version $Id: ApiDataProcessingServiceImpl.java, v 0.1 2017/7/17 10:36 Ian.Su Exp $
 */
@Service("defaultApiDataProcessingService")
public class ApiDataProcessingServiceImpl implements ApiDataProcessingService {

    @Override
    public Object processing(String address, Map<String, Object> externalParam, Map<String, Object> combineParams, Object rawData) {

        return JSONObject.parse((String)rawData);

    }
}
