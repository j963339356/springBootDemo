package com.wjc.assess.Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    //页面跳转，访问第一个页面，跳转到第二个页面
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/toLogin").setViewName("login");
//    }

    public void addInterceptors(InterceptorRegistry registry) {
        //拦截api后的所有
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/api/**");
    }
}
