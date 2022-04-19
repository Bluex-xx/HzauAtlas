package com.atlas.config.fliter;

import com.atlas.config.BodyReaderHttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "RequestWrapperFilter", urlPatterns = "/cat/search")
public class RequestWrapperFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

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

