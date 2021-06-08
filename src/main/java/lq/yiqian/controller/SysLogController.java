package lq.yiqian.controller;

import com.github.pagehelper.PageInfo;
import lq.yiqian.domain.SysLog;
import lq.yiqian.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 操作日志
 * @author LQ
 * @create 2020-06-10 12:25
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Resource
    ISysLogService sysLogService;

    /**
     * 查询所有
     * <p>
     * 带条件的查询
     *
     * @param page
     * @param condition
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                @RequestParam(name = "condition", defaultValue = "") String condition) {
        ModelAndView mv = new ModelAndView();
        List<SysLog> sysLogs = sysLogService.findByUri(condition, page, 30);
        PageInfo pageInfo = new PageInfo(sysLogs);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("condition", condition);
        mv.setViewName("/pages/admin/sysLog");
        return mv;
    }
}
