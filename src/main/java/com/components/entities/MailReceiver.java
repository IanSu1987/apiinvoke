package com.components.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 接收系统通知邮箱
 * Created by wish on 2017/7/18.
 */
@Entity
@Table(name = "mail_receiver")
public class MailReceiver implements Serializable {

    private static final long serialVersionUID = 24L;

    @Id
    @Column(columnDefinition = "varchar(100) NOT NULL COMMENT 'PK'")
    private String id;

    @Column(name = "email", columnDefinition = "varchar(100) NOT NULL COMMENT '邮箱' ")
    private String email;

    @Column(name = "remark", columnDefinition = "varchar(100) COMMENT '备注' ")
    private String remark;

    @Column(name = "gmt_create", insertable = false, updatable = false, columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间' ")
    private Date gmtCreate;

    @Column(name = "gmt_update", insertable = false, updatable = false, columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '修改时间' ")
    private Date gmtUpdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
