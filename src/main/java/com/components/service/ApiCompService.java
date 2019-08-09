package com.components.service;

import com.components.entities.CompApi;

import java.util.List;

/**
 *
 * comp_api相关数据查询
 *
 * @author Ian.Su
 * @version $Id: ApiCompService.java, v 0.1 2017/7/14 16:46 Ian.Su Exp $
 */
public interface ApiCompService {


    /**
     *
     * 根据id查询 api配置信息
     *
     * */
    CompApi get(String id);


    /**
     *
     * 查询所有接口文档地址
     *
     * */
    List<CompApi> finddocumentAddress();

}
