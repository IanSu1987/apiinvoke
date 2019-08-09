package com.components.mapper;

import com.components.entities.CompApi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * @author Ian.Su
 * @version $Id: CompApiMapper.java, v 0.1 2017/7/6 10:01 Ian.Su Exp $
 */
@Mapper
public interface CompApiMapper {


    @Select("select * from comp_api where id=#{id}")
    CompApi get( String id );

    @Select("select * from comp_api")
    List<CompApi> finddocumentAddress();

}
