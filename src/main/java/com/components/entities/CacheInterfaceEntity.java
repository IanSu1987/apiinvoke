package com.components.entities;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: 缓存公用方法接口
 *
 * @author: GanZiB
 * Date: 2019-04-30
 * Time: 17:36
 */
public interface CacheInterfaceEntity {

    /**
     * 获取Id
     *
     * @return
     */
    String getId();

    /**
     * 设置ID
     *
     * @param id
     */
    void setId(String id);

    /**
     * 获取值
     *
     * @return
     */
    String getVal();

    /**
     * 设置值
     *
     * @param val
     */
    void setVal(String val);

    /**
     * 获取有效时间
     *
     * @return
     */
    Date getValidDate();

    /**
     * 设置有效时间
     *
     * @param validDate
     */
    void setValidDate(Date validDate);

    /**
     * 获取创建时间
     *
     * @return
     */
    Date getGmtCreate();

    /**
     * 设置创建时间
     *
     * @param gmtCreate
     */
    void setGmtCreate(Date gmtCreate);

    /**
     * 获取更新时间
     *
     * @return
     */
    Date getGmtUpdate();

    /**
     * 设置更新时间
     *
     * @param gmtUpdate
     */
    void setGmtUpdate(Date gmtUpdate);
}
