package com.components.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="logging_event")
public class LoggingEvent implements Serializable {

    private static final long serialVersionUID = 24L;

    @Id
    @Column(name="event_id",columnDefinition = "BIGINT AUTO_INCREMENT NOT NULL COMMENT 'PK'")
    private Long eventId;

    @Column(name="timestmp",columnDefinition = "BIGINT NOT NULL  COMMENT '生成时间'")
    private Date timestmp;

    @Column(name="formatted_message",columnDefinition = "LONGTEXT NOT NULL  COMMENT '日志信息内容'")
    private String formattedMessage;

    @Column(name="logger_name",columnDefinition = "VARCHAR(254) NOT NULL  COMMENT '日志名称, 一般已产生的代码的信息命名 如：mavenDemos.TestLogback.Test'")
    private String loggerName;

    @Column(name="level_string",columnDefinition = "VARCHAR(254) NOT NULL  COMMENT '日志级别 如：INFO,ERROR等'")
    private String levelString;

    @Column(name="thread_name",columnDefinition = "VARCHAR(254)  COMMENT '线程名称  如：main'")
    private String threadName;

    @Column(name="reference_flag",columnDefinition = "SMALLINT")
    private int referenceFlag;

    @Column(name="arg0",columnDefinition = "VARCHAR(254)")
    private String arg0;

    @Column(name="arg1",columnDefinition = "VARCHAR(254)")
    private String arg1;

    @Column(name="arg2",columnDefinition = "VARCHAR(254)")
    private String arg2;

    @Column(name="arg3",columnDefinition = "VARCHAR(254)")
    private String arg3;

    @Column(name="caller_filename",columnDefinition = "VARCHAR(254) NOT NULL  COMMENT '产生日志的代码名称  如：Test.java'")
    private String callerFilename;

    @Column(name="caller_class",columnDefinition = "VARCHAR(254) NOT NULL  COMMENT 'CLASS 名称 如：mavenDemos.TestLogback.Test'")
    private String callerClass;

    @Column(name="caller_method",columnDefinition = "VARCHAR(254) NOT NULL  COMMENT '产生日志的具体方法 如：main'")
    private String callerMethod;

    @Column(name="caller_line",columnDefinition = "CHAR(4) NOT NULL  COMMENT '代码行数  如：25'")
    private String callerLine;

    @Column(name = "gmt_create",insertable = false , updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '日志产生时间'")
    private Date gmtCreate;

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getTimestmp() {
        return timestmp;
    }

    public void setTimestmp(Date timestmp) {
        this.timestmp = timestmp;
    }

    public String getFormattedMessage() {
        return formattedMessage;
    }

    public void setFormattedMessage(String formattedMessage) {
        this.formattedMessage = formattedMessage;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getLevelString() {
        return levelString;
    }

    public void setLevelString(String levelString) {
        this.levelString = levelString;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public int getReferenceFlag() {
        return referenceFlag;
    }

    public void setReferenceFlag(int referenceFlag) {
        this.referenceFlag = referenceFlag;
    }

    public String getArg0() {
        return arg0;
    }

    public void setArg0(String arg0) {
        this.arg0 = arg0;
    }

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    public String getArg3() {
        return arg3;
    }

    public void setArg3(String arg3) {
        this.arg3 = arg3;
    }

    public String getCallerFilename() {
        return callerFilename;
    }

    public void setCallerFilename(String callerFilename) {
        this.callerFilename = callerFilename;
    }

    public String getCallerClass() {
        return callerClass;
    }

    public void setCallerClass(String callerClass) {
        this.callerClass = callerClass;
    }

    public String getCallerMethod() {
        return callerMethod;
    }

    public void setCallerMethod(String callerMethod) {
        this.callerMethod = callerMethod;
    }

    public String getCallerLine() {
        return callerLine;
    }

    public void setCallerLine(String callerLine) {
        this.callerLine = callerLine;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}
