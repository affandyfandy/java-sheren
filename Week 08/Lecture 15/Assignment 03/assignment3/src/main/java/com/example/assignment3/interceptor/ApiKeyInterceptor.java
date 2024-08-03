package com.example.assignment3.interceptor;

import com.example.assignment3.entity.ApiKey;
import com.example.assignment3.repository.ApiKeyRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor {

    private final ApiKeyRepository apiKeyRepository;

    @Autowired
    public ApiKeyInterceptor(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String apiKeyHeader = request.getHeader("api-key");

        Optional<ApiKey> apiKeyOptional = apiKeyRepository.findByKey(apiKeyHeader);
        if (apiKeyHeader == null || apiKeyOptional.isEmpty()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid API key");
            return false;
        }

        ApiKey apiKey = apiKeyOptional.get();
        apiKey.setLastUsed(LocalDateTime.now());
        apiKeyRepository.save(apiKey);

        response.addHeader("username", apiKey.getUsername());

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        response.addHeader("timestamp", timestamp);

        request.setAttribute("username", apiKey.getUsername());
        request.setAttribute("timestamp", timestamp);

        return true;

    }
}
