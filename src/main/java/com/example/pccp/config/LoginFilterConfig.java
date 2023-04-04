package com.example.pccp.config;

import com.example.pccp.filter.LoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginFilterConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginFilter())
                //拦截的路径
                .addPathPatterns("/api/**")
                //排除登录接口
                .excludePathPatterns("/api/add")
                .excludePathPatterns("/api/websocket/**")
                .excludePathPatterns("/api/login")
                .excludePathPatterns("/api/SystemConstant/getSystemName")
                .excludePathPatterns("/api/SystemConstant/getSystemBgUrl")
                .excludePathPatterns("/api/InnerTempSensorInfo/addByRemoteDev");
    }
}
