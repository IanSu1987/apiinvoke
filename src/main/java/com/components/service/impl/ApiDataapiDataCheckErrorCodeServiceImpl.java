package com.components.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.components.exception.ApiException;
import com.components.service.ApiAddressParamAssemblyInterface;
import com.components.service.ApiDataCheckService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Ian.Su
 * @version $Id: ApiDataapiDataCheckServiceImpl.java, v 0.1 2017/7/10 15:58 Ian.Su Exp $
 */
@Service("dataapiDataCheckErrorCodeService")
public class ApiDataapiDataCheckErrorCodeServiceImpl implements ApiDataCheckService, ApiAddressParamAssemblyInterface {

    @Override
    public boolean checkData(String address, Map<String, Object> params, Object value) throws Exception {

        JSONObject object = (JSONObject) JSONObject.parse(value.toString());

        if (object.containsKey("error_code") && "0".equals("" + object.get("error_code"))) {
            return true;
        }

        String msg = assembly(address, params, value);

        throw new ApiException(msg);

    }
}
