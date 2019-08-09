package com.components.repository;

import com.components.entities.EsCompCache;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-04-29
 * Time: 18:10
 */
public interface EsCompCacheRepository extends ElasticsearchRepository<EsCompCache,String> {
}
