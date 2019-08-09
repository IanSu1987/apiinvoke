package com.components.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "logging_event_exception")
public class LoggingEventException implements Serializable {

    private static final long serialVersionUID = 24L;

    @Id
    @Column(name = "event_id",columnDefinition = "BIGINT NOT NULL  COMMENT 'PK'")
    private int eventId;

    @Id
    @Column(columnDefinition = "SMALLINT  NOT NULL  COMMENT 'PK'")
    private int i;

    @Column(name = "trace_line",columnDefinition = "VARCHAR(4000) NOT NULL COMMENT '具体错误日志内容  如：java.lang.NullPointerException: null'")
    private String traceLine;

    @Column(name = "gmt_create",insertable = false , updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '日志产生时间'")
    private Date gmtCreate;


    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getTraceLine() {
        return traceLine;
    }

    public void setTraceLine(String traceLine) {
        this.traceLine = traceLine;
    }


}
