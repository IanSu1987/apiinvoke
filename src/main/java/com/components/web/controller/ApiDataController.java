package com.components.web.controller;

import com.components.service.ApiCompService;
import com.components.service.ApiExecuteEngineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Ian.Su
 * @version $Id: ApiDataController.java, v 0.1 2017/7/6 17:38 Ian.Su Exp $
 */
@SuppressWarnings("ALL")
@Api(value = "/api", tags = "获取第三方API接口数据")
@RestController
@RequestMapping("api")
public class ApiDataController {


    Logger LOGGER = LoggerFactory.getLogger(ApiDataController.class);

    @Resource(name = "defaultApiExecEngine")
    private ApiExecuteEngineService defaultApiExecEngine;


    @Resource(name = "testApiExecEngine")
    private ApiExecuteEngineService testApiExecEngine;


    @Resource
    private ApiCompService apiCompService;


    @ApiOperation(value = "查看接口文档",notes = "根据document_address获取数据，其他的参考参实际接口文档")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "document_address",
                              paramType = "query",
                              value = "查看接口文档的字段",
                              required = true ,
                              dataType = "String" )
    })
    @RequestMapping(value = {"/documentAddress","/documentAddress.do"},method = {RequestMethod.POST,RequestMethod.GET})
    public Object finddocumentAddress (){

        return apiCompService.finddocumentAddress();
    }



    @ApiOperation(value = "接口调用", notes = "根据apiID获取数据，其他的参数参考实际接口文档")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apiId",
                    paramType = "query",
                    value = "要调用的api的id",
                    required = true,
                    dataType = "String")
    })
    @RequestMapping(value = {"/data" , "/data.do"},method = {RequestMethod.POST,RequestMethod.GET})
    public Object getApiData(@RequestParam String apiId, HttpServletRequest request) throws Exception {

        Map<String,Object> externalParam = requestParam(request);

        return defaultApiExecEngine.execute(apiId , externalParam );

    }


    @ApiOperation(value = "对接口测试调用", notes = "根据apiId来调用具体的接口,当没有传递参数,则默认用数据库配置的测试参数,当传递参数时用传递的参数覆盖数据配置的测试参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apiId",
                    paramType = "query",
                    value = "要调用的api的id",
                    required = true,
                    dataType = "String")
    })
    @RequestMapping(path = {"/test","/test.do"},method = {RequestMethod.POST,RequestMethod.GET})
    public Object apiTest(@RequestParam String apiId, HttpServletRequest request) throws Exception {

        Map<String,Object> externalParam = requestParam(request);

        return testApiExecEngine.execute(apiId , externalParam );

    }


    /**
     * 获取 request 中的参数
     *
     * */
    private Map<String,Object> requestParam(HttpServletRequest request){

        Map<String,String[]> params = request.getParameterMap();

        Iterator<Map.Entry<String, String[]>> it = params.entrySet().iterator();

        Map<String,Object> externalParam = new HashMap<>();

        while(it.hasNext()){
            Map.Entry<String, String[]> entry = it.next();
            String [] vals = entry.getValue();
            externalParam.put(entry.getKey() ,StringUtils.arrayToDelimitedString(vals,","));
        }

        return externalParam;
    }


    @ApiOperation(value = "接口调用", notes = "根据apiID获取数据，其他的参数参考实际接口文档")
    @RequestMapping(path = {"/data/by/map/{apiId}.do","/data/by/map/{apiId}"},method = {RequestMethod.POST,RequestMethod.GET})
    public Object apiInvokeByMap(@PathVariable String apiId,@RequestBody Map<String,Object> paramMap) throws Exception {

        return testApiExecEngine.execute(apiId , paramMap );

    }

    @ApiOperation(value = "接口调用", notes = "根据apiID获取数据，其他的参数参考实际接口文档")
    @RequestMapping(path = {"/data/{apiId}.do","/data/{apiId}"},method = {RequestMethod.POST,RequestMethod.GET})
    public Object apiData(@PathVariable String apiId, HttpServletRequest request) throws Exception {

        Map<String,Object> externalParam = requestParam(request);

        return testApiExecEngine.execute(apiId , externalParam );

    }

}
