package com.components.mapper;

import com.components.entities.LoggingEvent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Ian.Su
 * @version $Id: SystemLogMapper.java, v 0.1 2017/7/14 10:51 Ian.Su Exp $
 */
@Mapper
public interface SystemLogMapper {


    @Select("  select FROM_UNIXTIME( a.timestmp/1000,'%Y-%m-%d %H:%i:%S') create_time , a.* " +
            "  from logging_event a " +
            "  where timestmp >= #{start} and timestmp<=#{end} " +
            "  order by timestmp desc " +
            "  limit ${limitStart},${limitSize} ")
    List<Map<String, Object>> queryLogEvent(@Param("start") Long start,
                                            @Param("end") Long end,
                                            @Param("limitStart") Integer limitStart,
                                            @Param("limitSize") Integer limitSize);

    @Select("  select FROM_UNIXTIME( a.timestmp/1000 ,'%Y-%m-%d %H:%i:%S' ) create_time , a.* " +
            "  from logging_event a " +
            "  where event_id = #{event_id} ")
    Map<String, Object> logDetail(long event_id);


    @Select(" select * from logging_event_exception where event_id=#{event_id} order by i ")
    List<Map<String, Object>> logExceptionDetail(long event_id);

    @Select("select event_id eventid,arg0,arg1,arg2,arg3,caller_class callerclass,caller_filename callerclass,caller_line callerline,caller_method callermethod," +
            "formatted_message formattedmessage,level_string levelstring,logger_name loggername,reference_flag referenceflag,thread_name threadname,FROM_UNIXTIME(timestmp/1000) timestmp" +
            " from logging_event where level_string = 'ERROR'" +
            " and timestmp >= #{second}" +
            " and formatted_message like '%第三方接口异常%'" +
            " order by timestmp desc")
    List<LoggingEvent> findApiErrorEventAfter(long second);

}
