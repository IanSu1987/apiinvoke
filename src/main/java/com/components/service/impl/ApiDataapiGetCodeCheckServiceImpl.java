package com.components.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.components.exception.ApiException;
import com.components.service.ApiAddressParamAssemblyInterface;
import com.components.service.ApiDataCheckService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Ian.Su
 */
@Service("apiDataapiGetCodeCheckService")
public class ApiDataapiGetCodeCheckServiceImpl  implements ApiDataCheckService , ApiAddressParamAssemblyInterface {
	@Override
	public boolean checkData(String address, Map<String, Object> params, Object value) throws Exception {

		JSONObject jsonObject = (JSONObject) JSONObject.parse(value.toString());

		if ("0".equals(jsonObject.getString("code"))) {

			return  true;
		}

		String msg = assembly(address , params , value);

		throw new ApiException(msg);
	}
}
