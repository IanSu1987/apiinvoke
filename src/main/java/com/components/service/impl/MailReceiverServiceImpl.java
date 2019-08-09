package com.components.service.impl;

import com.components.entities.MailReceiver;
import com.components.mapper.MailReceiverMapper;
import com.components.service.MailReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wish on 2017/7/18.
 */
@SuppressWarnings("ALL")
@Service
public class MailReceiverServiceImpl implements MailReceiverService {
    @Autowired
    private MailReceiverMapper mailReceiverMapper;

    @Override
    public List<MailReceiver> findAllMailReceivers() {
        return mailReceiverMapper.findAllMailReceivers();
    }
}
