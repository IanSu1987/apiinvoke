package com.components;

import com.alibaba.fastjson.JSONObject;
import com.components.service.ApiDataCheckService;
import com.components.service.ApiExecuteEngineService;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import jdk.nashorn.internal.parser.JSONParser;

import com.components.web.controller.ApiDataController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ComponentsApplication.class)
@WebAppConfiguration
public class ComponentsApplicationTests {


	@Autowired
	private WebApplicationContext context;
	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {

		mvc = MockMvcBuilders.webAppContextSetup(context).build();//建议使用这种
	}

	//测试
	@Test
	public void apiDataYqtp() throws Exception {
		MvcResult o = mvc.perform(MockMvcRequestBuilders.post("/api/data.do")
				.param("apiId","dataapi_hongjing_yqtp")
				.param("key","pic_rowkey:913bb98a8b8a355427af45c38658fe0c|_|2017-08-03|_|hj_p2peye")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		System.out.println(o.getResponse().getContentAsString());
		System.out.println("===============");
	}
  @Resource(name = "defaultApiExecEngine")
  private ApiExecuteEngineService defaultApiExecEngine;

  @Test
  public void contextLoads() throws Exception {
//    Map<String, Object> mapParms = new HashMap<>();
//    mapParms.put("companyId", "61a655da3b384a1692ceb47b6a1176e0");
//    Object o = defaultApiExecEngine.execute("api_bbd_jbxx", mapParms);
//    System.out.println(o.toString());
//    System.out.println("==============end==================");
    Map<String, Object> mapParms = new HashMap<>();
    mapParms.put("qyId", "001d95b17543435c830eb0a1164b0510");
    mapParms.put("q_c", "江苏大圆银泰商品合约交易市场有限公司");
    mapParms.put("q_type", 1);
    mapParms.put("s_type", 1);
    mapParms.put("pn", 1);
    mapParms.put("p_size", 20);
    Object o = defaultApiExecEngine.execute("api_dataapi_hongjing_cplist", mapParms);
    System.out.println(o.toString());
    System.out.println("==============end==================");


  }

}


















