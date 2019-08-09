package com.components.service.impl;

import com.components.entities.CompApi;
import com.components.mapper.CompApiMapper;
import com.components.service.ApiCompService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ian.Su
 * @version $Id: ApiCompServiceImpl.java, v 0.1 2017/7/14 16:47 Ian.Su Exp $
 */
@SuppressWarnings("ALL")
@Service
public class ApiCompServiceImpl implements ApiCompService {


    @Autowired
    private CompApiMapper compApiMapper;


    @Override
    public CompApi get(String id) {
        return compApiMapper.get(id);
    }

    @Override
    public List<CompApi> finddocumentAddress() {
        return compApiMapper.finddocumentAddress();
    }
}
