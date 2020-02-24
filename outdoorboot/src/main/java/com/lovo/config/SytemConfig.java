package com.lovo.config;

import com.lovo.util.MyInterceptors;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Component
public class SytemConfig implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new MyInterceptors())
               .addPathPatterns("/**")
               .excludePathPatterns("/gotoIndex","/login","/gotoLogin");
    }
}
