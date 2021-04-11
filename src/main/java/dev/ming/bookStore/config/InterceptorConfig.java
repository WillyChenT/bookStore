package dev.ming.bookStore.config;

import dev.ming.bookStore.interceptor.CorsInterceptor;
import dev.ming.bookStore.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }
    @Bean
    CorsInterceptor corsInterceptor(){
        return new CorsInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //所有請求皆需要執行跨域處理
        registry.addInterceptor(corsInterceptor()).addPathPatterns("/**");

        //登入驗證
        registry.addInterceptor(loginInterceptor())
                //攔截路徑
                .addPathPatterns("/api/v1/pri/*/*/**")
                //不攔截路徑
                .excludePathPatterns("/api/v1/pri/user/login","/api/v1/pri/user/register");

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
