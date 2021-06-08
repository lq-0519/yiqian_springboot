package lq.yiqian.service.impl;

import com.github.pagehelper.PageHelper;
import lq.yiqian.mapper.SysLogMapper;
import lq.yiqian.domain.SysLog;
import lq.yiqian.service.ISysLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 操作日志
 * @author LQ
 * @create 2020-06-10 11:59
 */
@Service
public class SysLogService implements ISysLogService {
    @Resource
    private SysLogMapper sysLogMapper;

    /**
     * 新增日志
     */
    @Override
    public void save(SysLog sysLog) {
        sysLogMapper.save(sysLog);
    }

    /**
     * 根据uri查询
     */
    @Override
    public List<SysLog> findByUri(String condition, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return sysLogMapper.findByMethod("%" + condition + "%");
    }
}
