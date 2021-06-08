package lq.yiqian.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 页面出错的过滤器
 * <p>
 * 需要拦截所有的资源
 * 页面出错了就跳转到指定页面
 *
 * @author LQ
 * @create 2020-07-04 18:20
 */
@WebFilter({"/*"})
public class ExceptionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {// 捕获错误
            chain.doFilter(request, response);
        } catch (Exception e) {
            // TODO: 2021/5/31 将错误信息入库
            // 存储错误信息
            request.setAttribute("errorMsg", e.getMessage());
            // 跳转到错误界面
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
