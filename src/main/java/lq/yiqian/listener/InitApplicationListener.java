package lq.yiqian.listener;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import lq.yiqian.domain.Variable;
import lq.yiqian.service.ISearchHistoryService;
import lq.yiqian.service.IVariableService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * @author LQ
 * @create 2020-06-29 11:05
 * <p>
 * 用于加载公告的webListener
 * <p>
 * 仅仅在启动时从数据库中加在公告, 并不会在服务器关闭时将session里面的数据存储到数据库,
 * 因为tomcat非正常关闭(不使用shutdown.bat关闭服务器)时contextDestroyed方法并不能正常执行
 */
@WebListener
@Log4j2
public class InitApplicationListener implements ServletContextListener {

    /**
     * 启动时从数据库中加在公告
     * <p>
     * 从数据库中加载公告需要使用noticeService对象, noticeService对象需要从WebApplicationContext中获取,
     * 不能使用注入的方式获取noticeService对象, 那是因为spring容器还没有建立起来, 通过注入是无法获取到对象的
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.warn("contextInitialized... 容器启动, 加载配置项");
        ServletContext servletContext = sce.getServletContext();
        // 获取spring容器对象
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        assert applicationContext != null;
        // 获取noticeService对象
        IVariableService variableService = (IVariableService) applicationContext.getBean("variableService");
        // 获取searchHistory的bean对象
        ISearchHistoryService searchHistoryService = (ISearchHistoryService) applicationContext.getBean("searchHistoryService");
        //从数据库中加载
        List<Variable> variables = variableService.findAll();
        log.warn("contextInitialized, 数据库变量variables:{}", JSON.toJSONString(variables));
        // 添加到servletContext中
        for (Variable variable : variables) {
            servletContext.setAttribute(variable.getName(), variable.getValue());
        }
        // 获取总数
        Integer searchTotal = searchHistoryService.getTotalCount();
        // 设置总数
        servletContext.setAttribute("searchTotal", searchTotal + "");
        log.warn("contextInitialized... 容器启动启动完毕!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
