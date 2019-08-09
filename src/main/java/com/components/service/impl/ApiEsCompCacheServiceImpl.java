package com.components.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.components.entities.EsCompCache;
import com.components.repository.EsCompCacheRepository;
import com.components.service.ApiCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-04-29
 * Time: 18:49
 */
@SuppressWarnings("ALL")
@Service("esCacheService")
public class ApiEsCompCacheServiceImpl implements ApiCacheService {

    @Autowired
    private EsCompCacheRepository compCacheRepository;

    @Override
    public EsCompCache getCompCache(String key) {
        Optional<EsCompCache> optional = compCacheRepository.findById(key);
        return optional.orElse(null);
    }

    @Override
    public Object get(String key) {
        EsCompCache val = getCompCache(key);
        if (val == null || !StringUtils.hasText(val.getVal())) {
            return null;
        }
        return JSONObject.parse(val.getVal());
    }

    @Override
    public void set(String key, Date validate, Object value) {
        if (value == null) {
            return;
        }
        EsCompCache cache = new EsCompCache();
        if (null != get(key)) {
            cache.setGmtUpdate(new Date());
        } else {
            cache.setGmtCreate(new Date());
        }
        cache.setId(key);
        cache.setValidDate(validate());
        cache.setVal(value.toString());
        compCacheRepository.save(cache);
    }
}
