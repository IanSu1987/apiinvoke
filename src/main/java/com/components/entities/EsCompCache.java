package com.components.entities;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: 缓存
 *
 * @author: GanZiB
 * Date: 2019-04-29
 * Time: 17:56
 */
@Document(indexName = "ra6.comp_cache", shards = 3, type = "comp_cache", replicas = 1)
public class EsCompCache implements Serializable, CacheInterfaceEntity {
    private static final long serialVersionUID = 3242773692258971120L;

    @Id
    @Field(type = FieldType.Keyword)
    private String id;
    /**
     * 内容
     */
    @Field(type = FieldType.Text)
    private String val;
    /**
     * 有效期截止时间
     */
    @Field(type = FieldType.Date)
    private Date validDate;
    /**
     * 创建时间
     */
    @Field(type = FieldType.Date)
    private Date gmtCreate;
    /**
     * 修改时间
     */
    @Field(type = FieldType.Date)
    private Date gmtUpdate;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getVal() {
        return val;
    }

    @Override
    public void setVal(String val) {
        this.val = val;
    }

    @Override
    public Date getValidDate() {
        return validDate;
    }

    @Override
    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    @Override
    public Date getGmtCreate() {
        return gmtCreate;
    }

    @Override
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Override
    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    @Override
    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }
}
