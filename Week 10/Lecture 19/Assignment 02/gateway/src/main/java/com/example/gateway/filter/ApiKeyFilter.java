package com.example.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class ApiKeyFilter extends AbstractGatewayFilterFactory<Object> {

    private WebClient.Builder webClientBuilder;

    @Autowired
    public ApiKeyFilter(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public ApiKeyFilter() {
        super(Object.class);
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            HttpHeaders headers = request.getHeaders();
            String apiKey = headers.getFirst("api-key");

            if (apiKey == null || apiKey.isEmpty()) {
                return handleUnauthorizedResponse(exchange);
            }

            return webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8083/validateApiKey")
                    .header("api-key", apiKey)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .flatMap(isValid -> {
                        if (Boolean.TRUE.equals(isValid)) {
                            return chain.filter(exchange);
                        } else {
                            return handleUnauthorizedResponse(exchange);
                        }
                    });
        };
    }

    private Mono<Void> handleUnauthorizedResponse(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
