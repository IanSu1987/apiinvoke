package com.components.service.impl;

import com.components.exception.ApiException;
import com.components.service.ApiAddressParamAssemblyInterface;
import com.components.service.ApiInvokingService;
import org.apache.http.HttpHost;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.Map;

/**
 * @author Ian.Su
 * @version $Id: ApiHttGetInvokingService.java, v 0.1 2017/7/10 14:07 Ian.Su Exp $
 */
@Service("apiHttpGettInvoking")
public class ApiHttpGetInvokingServiceImpl implements ApiInvokingService,ApiAddressParamAssemblyInterface {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Value("${http.proxyHost:null}")
    private String proxyHost;

    @Value("${http.proxyPort:0}")
    private Integer proxyPort;

    @Override
    public Object invoking(String address, Map<String, Object> params) throws Exception {

        StringBuilder urlParams = new StringBuilder();

        try {

            for (Map.Entry<String, Object> entry : params.entrySet()) {

                urlParams.append(urlParams.length() == 0 ? "?" : "&")
                        .append( entry.getKey() ).append("=")
                        .append(URLEncoder.encode(entry.getValue().toString(), "utf8"));
            }

            urlParams.insert(0,address);

            LOGGER.debug(urlParams.toString());

            Request request = Request.Get(urlParams.toString()).
                    addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

            if(0 < proxyPort.intValue()){
                HttpHost proxy = new HttpHost(proxyHost, proxyPort);
                request = request.viaProxy(proxy);
            }

            return request.execute().returnContent().asString();

        }catch (Exception e){

            throw new ApiException( assembly(address, params, e)  ,e);

        }



    }

}
