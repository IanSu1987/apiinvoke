package com.components.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.components.dao.CompCacheDao;
import com.components.entities.CompCache;
import com.components.mapper.CompCacheMapper;
import com.components.service.ApiCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.PersistenceProvider;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.Date;

/**
 * @author Ian.Su
 * @version $Id: ApiMysqlCacheServiceImpl.java, v 0.1 2017/7/11 11:22 Ian.Su Exp $
 */
@SuppressWarnings("ALL")
@Service("mysqlCacheService")
public class ApiMysqlCacheServiceImpl implements ApiCacheService {


    @Autowired
    private EntityManager em;

    @Autowired
    private CompCacheDao cacheDao;

    @Autowired
    private CompCacheMapper cacheMapper ;


    @Override
    public CompCache getCompCache(String key) {

        return cacheDao.findById(key).orElse(null);
    }

    @Override
    public Object get(String key) {

        CompCache val = getCompCache( key);

        if(val==null || !StringUtils.hasText(val.getVal())){
            return null;
        }

        return JSONObject.parse(val.getVal());
    }




    @Override
    public void set(String key, Date validate , Object value) {

        if(value == null ){
            return;
        }

        CompCache cache = new CompCache();

        cache.setId(key);

        cache.setValidDate(validate());

        cache.setVal(value.toString());

        cacheDao.save(cache);

    }



}
