package com.example.tallking.config;


import com.example.tallking.interceptor.Tokeninterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    Tokeninterceptor tokeninterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
         InterceptorRegistration interceptor=registry.addInterceptor(tokeninterceptor);

         //"/api/user/createuser",
        // "/api/user/create",  login
        // "/api/user/createuserp",  zhuce
        // "/api/token/createtoken",
        // "/api/sms/createsms"   duanxin

         interceptor.addPathPatterns("/api/**")
                    .excludePathPatterns("/error","/api/user/create");


    }



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/static/uploads/")
                .addResourceLocations("classpath:/static/end/")
                .addResourceLocations("classpath:/uploads/")
                .addResourceLocations("classpath:/public/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET","POST","PUT","DELETE","HEAD","OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //拦截 /end/page/**  不拦截/end/page/login.html
//        registry.addInterceptor(new MyInterceptor())
//                .addPathPatterns("/end/page/**")
//                .excludePathPatterns("/end/page/demo.html");
//    }
}
