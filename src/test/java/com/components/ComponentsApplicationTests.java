package com.components;


import com.components.web.controller.ApiDataController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

}


















