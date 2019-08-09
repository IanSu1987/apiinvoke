package com.components.dao;


import com.components.entities.CompCache;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ian.Su
 * @version $Id: CompCacheDao.java, v 0.1 2017/7/6 10:01 Ian.Su Exp $
 */

@Repository
public interface CompCacheDao extends PagingAndSortingRepository<CompCache, String> {

}
