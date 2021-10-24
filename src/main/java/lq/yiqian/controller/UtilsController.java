package lq.yiqian.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import lq.yiqian.dao.domain.InvitationCode;
import lq.yiqian.dao.query.InvitationCodeQuery;
import lq.yiqian.service.IUtilsService;
import lq.yiqian.service.impl.InvitationCodeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * mock工具类
 * 没有前端入口, 使用postMan操作
 *
 * @author LQ
 * @create 2021-05-06 21:04
 */
@Controller
@Log4j2
@RequestMapping("utils")
public class UtilsController {

    @Resource
    private IUtilsService utilsService;

    @Resource
    private InvitationCodeService invitationCodeService;

    /**
     * 将数据库中所有的数据迁移到es中
     */
    public void addAll() {
        utilsService.addAll();
    }

    /**
     * 创建索引
     */
    @RequestMapping("createIndex")
    @ResponseBody
    public void createIndex() {
        utilsService.createIndex();
    }

    /**
     * 测试
     */
    @RequestMapping("testES")
    @ResponseBody
    public String testES() {
        utilsService.testES();
        return "任务提交";
    }

    /**
     * 数据转移
     */
    @RequestMapping("dataTransferToES")
    @ResponseBody
    public String dataTransferToES() {
        utilsService.dataTransferToES();
        return "数据转移任务提交!";
    }

    /**
     * 更新总搜索次数
     */
    @ResponseBody
    @RequestMapping("updateSearchNum")
    public String updateSearchNum() {
        utilsService.updateSearchNum();
        return "更新总搜索次数 任务提交";
    }

    /**
     * 测试xml文件是否好使
     */
    @ResponseBody
    @RequestMapping("testMapper")
    public String testMapper() {
        InvitationCodeQuery invitationCodeQuery = new InvitationCodeQuery();
        List<InvitationCode> invitationCodeList = invitationCodeService.queryForList(invitationCodeQuery, 1, 10);
        log.warn(JSON.toJSONBytes(invitationCodeList));
        return "任务提交";
    }
}
