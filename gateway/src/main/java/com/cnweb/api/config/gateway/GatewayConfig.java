package com.cnweb.api.config.gateway;

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
                .route("auth", r -> r.path("/api/auth/**").uri("http://localhost:8081"))
                .route("profile", r -> r.path("/api/profile/**").uri("http://localhost:8082"))
                .build();
    }
}
