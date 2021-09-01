package com.sass.test.tenant.interceptor;

import com.sass.test.tenant.config.TenantContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Interceptor to check which database instance to load

@Component
public class DataSourceInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        TenantContextHolder.setTenantId(request.getHeader("s_id"));
        return super.preHandle(request, response, handler);
    }
}
