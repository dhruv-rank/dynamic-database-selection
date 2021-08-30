package com.sass.test.interceptor;

import com.sass.test.config.SaasContextHolder;
import com.sass.test.model.SaasNumber;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Interceptor to check which database instance to load

@Component
public class DataSourceInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String branch = request.getHeader("s_id");
        switch (branch) {
            case "saas_1":
                SaasContextHolder.setSaasNumber(SaasNumber.SAAS_1);
                break;
            case "saas_2":
                SaasContextHolder.setSaasNumber(SaasNumber.SAAS_2);
                break;
        }
        return super.preHandle(request, response, handler);
    }
}
