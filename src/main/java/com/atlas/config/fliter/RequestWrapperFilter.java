package com.atlas.config.fliter;

import com.atlas.config.BodyReaderHttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.maven.shared.utils.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@WebFilter(filterName = "RequestWrapperFilter",urlPatterns = "/*",
        initParams = @WebInitParam(name = "noFilterUrl", value = "/search"))
public class RequestWrapperFilter implements Filter {

    private List<String> noFilterUrls;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 从过滤器配置中获取initParams参数
        String noFilterUrl = filterConfig.getInitParameter("noFilterUrl");
        // 将排除的URL放入成员变量noFilterUrls中
        if (StringUtils.isNotBlank(noFilterUrl)) {
            noFilterUrls = new ArrayList<>(Arrays.asList(noFilterUrl.split(",")));
        }
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException
            , IOException {
        ServletRequest requestWrapper = null;
        if (request instanceof HttpServletRequest) {
            requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) request);
        }
        if (null == requestWrapper) {
            log.error("过滤器包装request失败!将返回原来的request");
            chain.doFilter(request, response);
        } else {
            log.info("过滤器包装request成功");
            chain.doFilter(requestWrapper, response);
        }
    }

    @Override
    public void destroy() {

    }

}

