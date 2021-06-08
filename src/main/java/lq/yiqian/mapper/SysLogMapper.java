package lq.yiqian.mapper;

import lq.yiqian.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 操作日志
 * @author LQ
 * @create 2020-06-10 12:04
 */
public interface SysLogMapper extends Mapper<SysLog> {
    /**
     * 新增日志
     */
    @Insert("insert into sysLog(visitTime, ip, executionTime, uri, browser, operatingSystem )" +
            " values(#{visitTime}, #{ip},  #{executionTime}, #{uri}, #{browser}, #{operatingSystem})")
    void save(SysLog sysLog);

    /**
     * 根据uri查询
     */
    @Select("select id, visitTime, ip, uri, executionTime, browser, operatingSystem from sysLog " +
            "where  uri like #{s} order by visitTime desc ")
    List<SysLog> findByMethod(String s);
}
