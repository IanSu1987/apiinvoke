package com.components.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.components.exception.ApiException;
import com.components.service.ApiAddressParamAssemblyInterface;
import com.components.service.ApiDataCheckService;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;

/**
 * @author Ian.Su
 * @version $Id: ApiDataapiDataCheckServiceImpl.java, v 0.1 2017/7/10 15:58 Ian.Su Exp $
 */
@Service("responseBeanDataCheckService")
public class ApiResponseBeanDataCheckServiceImpl implements ApiDataCheckService , ApiAddressParamAssemblyInterface {



    @Override
    public boolean checkData(String address, Map<String, Object> params, Object value) throws Exception {

        JSONObject object = (JSONObject)JSONObject.parse(value.toString());

        if(object.containsKey("success") && "true".equals(String.valueOf(object.get("success"))) ){
            return true;
        }

        String msg = assembly(address,  params,  value);

        throw new ApiException(msg);

    }
}
