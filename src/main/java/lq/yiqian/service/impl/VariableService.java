package lq.yiqian.service.impl;

import lq.yiqian.mapper.VariableMapper;
import lq.yiqian.domain.Variable;
import lq.yiqian.service.IVariableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LQ
 * @create 2020-07-04 12:06
 */
@Service("variableService")
public class VariableService implements IVariableService {

    @Resource
    private VariableMapper variablemapper;

    /**
     * 查询所有
     */
    @Override
    public List<Variable> findAll() {
        return variablemapper.findAll();
    }

    /**
     * 根据键更新值
     */
    @Override
    public void updateByName_value(String name, String value) {
        variablemapper.updateByName_value(name, value);
    }

    /**
     * 更新页脚信息
     */
    @Override
    public void updateFooterInfo(String qqGroup, String adminQQ) {
        variablemapper.updateByName_value("qqGroup", qqGroup);
        variablemapper.updateByName_value("adminQQ", adminQQ);
    }

    /**
     * 更新搜索次数
     */
    @Override
    public void updateSearchTotal(String searchTotal, String value) {
        variablemapper.updateByName_value(searchTotal, value);
    }
}
