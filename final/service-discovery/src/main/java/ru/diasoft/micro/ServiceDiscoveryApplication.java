package ru.diasoft.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * ServiceDiscoveryApplication
 *
 * @author Skyhunter
 * @date 16.04.2021
 */
@EnableEurekaServer
@SpringBootApplication
public class ServiceDiscoveryApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceDiscoveryApplication.class, args);
    }
}