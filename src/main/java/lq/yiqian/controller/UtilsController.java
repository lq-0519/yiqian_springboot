package lq.yiqian.controller;

import lq.yiqian.service.IUtilsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * mock工具类
 * 没有前端入口, 使用postMan操作
 *
 * @author LQ
 * @create 2021-05-06 21:04
 */
@Controller
@RequestMapping("utils")
public class UtilsController {
    @Resource
    private IUtilsService utilsService;

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
    public String updateSearchNum(){
        utilsService.updateSearchNum();
        return "更新总搜索次数 任务提交";
    }
}
