package com.components.service;

import com.components.entities.MailReceiver;

import java.util.List;

/**
 * 邮件接收service
 * Created by wish on 2017/7/18.
 */
public interface MailReceiverService {
    /**
     * 查询所有的邮件接收邮箱
     *
     * @return
     */
    List<MailReceiver> findAllMailReceivers();
}
