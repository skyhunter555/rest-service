package ru.diasoft.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * GatewayApplication
 *
 * @author Skyhunter
 * @date 16.04.2021
 */
@SpringBootApplication
@EnableEurekaClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator createRoutes(RouteLocatorBuilder builder, DiscoveryClient discoveryClient) {

        ServiceInstance serviceInstanceFirst = discoveryClient.getInstances("skyhunter-demo-service").get(0);

        return builder.routes()
                .route(p -> p
                        .path("/v1/sms-verification/**")
                        .uri("http://" + serviceInstanceFirst.getHost() + ":" + serviceInstanceFirst.getPort() + "/")
                        .id("skyhunter-demo")
                )
                .build();
    }
}
