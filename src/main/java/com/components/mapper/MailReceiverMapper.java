package com.components.mapper;

import com.components.entities.MailReceiver;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 邮件接收mapper
 * Created by wish on 2017/7/18.
 */
@Mapper
public interface MailReceiverMapper {
    /**
     * 查询所有的邮件接收邮箱
     *
     * @return
     */
    @Select("select id,email,remark,gmt_create gmtcreate,gmt_create gmtcreate from mail_receiver")
    List<MailReceiver> findAllMailReceivers();
}
