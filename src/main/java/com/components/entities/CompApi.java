package com.components.entities;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Columns;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * api 接口配置表
 *
 * @author Ian.Su
 * @version $Id: CompApi.java, v 0.1 2017/7/5 11:27 Ian.Su Exp $
 */
@Entity
@Table(name = "comp_api")
public class CompApi implements Serializable {

    private static final long serialVersionUID = 24L;

    @Id
    @Column(columnDefinition = "varchar(100) NOT NULL COMMENT 'PK'")
    private String id;

    @Column(name = "timer_poll", columnDefinition = "int COMMENT '间隔测试时间,单位:秒' ")
    private Integer timerPoll;


    @Column(name = "fix_param", columnDefinition = "varchar(1000) COMMENT '固定参数' ")
    private String fixParam;


    @Column(name = "test_param", columnDefinition = "varchar(1000) COMMENT '测试参数' ")
    private String testParam;


    @Column(name = "external_param", columnDefinition = "varchar(200) COMMENT '外部参数,格式如:{\"exclude\":[\"a\",...]} 或  {\"include\":[\"a\",....]}' ")
    private String externalParam;


    @Column(name = "combine_param_interface", columnDefinition = "varchar(200) NOT NULL COMMENT '参数组合接口' ")
    private String combineParamInterface;

    @Column(name = "check_param_interface", columnDefinition = "varchar(200) COMMENT '参数校验接口' ")
    private String checkParamInterface;


    @Column(name = "api_url", columnDefinition = "varchar(500) NOT NULL COMMENT '远程接口地址' ")
    private String apiUrl;


    @Column(name = "invoking_interface", columnDefinition = "varchar(500) NOT NULL COMMENT '接口调用服务' ")
    private String invokingInterface;


    @Column(name = "data_check_interface", columnDefinition = "varchar(500) NOT NULL COMMENT '数据校验服务' ")
    private String dataCheckInterface;


    @Column(name = "data_processing_interface", columnDefinition = "varchar(500) NOT NULL COMMENT '数据处理服务' ")
    private String dataProcessingInterface;

    @Column(name = "exception_interface", columnDefinition = "varchar(200) NOT NULL COMMENT '异常处理服务' ")
    private String exceptionInterface;


    @Column(name = "cache_interface", columnDefinition = "varchar(200) NOT NULL COMMENT '异常处理服务' ")
    private String cacheInterface;


    @Column(name = "api_description", columnDefinition = "varchar(500) NOT NULL COMMENT 'api描述' ")
    private String apiDescription;

    @Column(name = "gmt_create", insertable = false, updatable = false, columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间' ")
    private Date gmtCreate;


    @Column(name = "gmt_update", insertable = false, updatable = false, columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '修改时间' ")
    private Date gmtUpdate;


    @Column(name = "document_address", columnDefinition = "varchar(500)  COMMENT ' 文档地址'")
    private String documentAddress;


    public String getDocumentAddress() {
        return documentAddress;
    }

    public void setDocumentAddress(String documentAddress) {
        this.documentAddress = documentAddress;
    }

    public String getCombineParamInterface() {
        return combineParamInterface;
    }

    public void setCombineParamInterface(String combineParamInterface) {
        this.combineParamInterface = combineParamInterface;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTimerPoll() {
        return timerPoll;
    }

    public void setTimerPoll(Integer timerPoll) {
        this.timerPoll = timerPoll;
    }

    public String getFixParam() {
        return fixParam;
    }

    public void setFixParam(String fixParam) {
        this.fixParam = fixParam;
    }

    public String getTestParam() {
        return testParam;
    }

    public void setTestParam(String testParam) {
        this.testParam = testParam;
    }

    public String getExternalParam() {
        return externalParam;
    }

    public void setExternalParam(String externalParam) {
        this.externalParam = externalParam;
    }

    public String getCheckParamInterface() {
        return checkParamInterface;
    }

    public void setCheckParamInterface(String checkParamInterface) {
        this.checkParamInterface = checkParamInterface;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getInvokingInterface() {
        return invokingInterface;
    }

    public void setInvokingInterface(String invokingInterface) {
        this.invokingInterface = invokingInterface;
    }

    public String getDataCheckInterface() {
        return dataCheckInterface;
    }

    public void setDataCheckInterface(String dataCheckInterface) {
        this.dataCheckInterface = dataCheckInterface;
    }

    public String getDataProcessingInterface() {
        return dataProcessingInterface;
    }

    public void setDataProcessingInterface(String dataProcessingInterface) {
        this.dataProcessingInterface = dataProcessingInterface;
    }

    public String getExceptionInterface() {
        return exceptionInterface;
    }

    public void setExceptionInterface(String exceptionInterface) {
        this.exceptionInterface = exceptionInterface;
    }

    public String getCacheInterface() {
        return cacheInterface;
    }

    public void setCacheInterface(String cacheInterface) {
        this.cacheInterface = cacheInterface;
    }

    public String getApiDescription() {
        return apiDescription;
    }

    public void setApiDescription(String apiDescription) {
        this.apiDescription = apiDescription;
    }
}
