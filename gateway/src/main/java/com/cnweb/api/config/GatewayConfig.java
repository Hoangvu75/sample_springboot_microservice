package com.cnweb.api.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        System.out.println("setup routes");
        return builder.routes()
                .route("authservice", r -> r.path("/api/auth/**")
                        .uri("http://localhost:8081"))
                .build();
    }
}
