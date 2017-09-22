package com.components.mapper;

import com.components.entities.CompApi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
 * @author Ian.Su
 * @version $Id: CompApiMapper.java, v 0.1 2017/7/6 10:01 Ian.Su Exp $
 */
@Mapper
public interface CompCacheMapper {


    @Select("select val from comp_cache where id=#{id}")
    String getVal(@Param("id") String id);

}
