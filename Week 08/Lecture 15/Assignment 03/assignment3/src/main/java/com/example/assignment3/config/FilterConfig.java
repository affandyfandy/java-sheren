package com.example.assignment3.config;

import com.example.assignment3.filter.ApiKeyFilter;
import com.example.assignment3.repository.ApiKeyRepository;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<ApiKeyFilter> apiKeyFilter(ApiKeyRepository apiKeyRepository) {
        FilterRegistrationBean<ApiKeyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ApiKeyFilter(apiKeyRepository));
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }
}