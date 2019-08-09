package com.components.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.components.exception.ApiException;
import com.components.service.ApiCheckParamService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * @author Ian.Su
 * @version $Id: ApiCheckParamServiceImpl.java, v 0.1 2017/7/10 11:24 Ian.Su Exp $
 */
@Service("defaultApiCheckParamService")
public class ApiCheckParamServiceImpl implements ApiCheckParamService {


    @Override
    public Map<String, Object> checkParam(Map<String, Object> sourceParam, String includeCheckJson) throws ApiException {

        if (!StringUtils.hasText(includeCheckJson)) {
            return sourceParam;
        }

        JSONObject includeObj = (JSONObject) JSONObject.parse(includeCheckJson);
        if (!includeObj.containsKey("include")) {
            return sourceParam;
        }

        JSONArray ary = (JSONArray) includeObj.get("include");

        Iterator<Object> it = ary.iterator();
        while (it.hasNext()) {
            Object key = it.next();
            if (!sourceParam.containsKey(key)) {
                throw new ApiException("参数 " + key + " 为必传参数.");
            }
        }

        return sourceParam;
    }
}
