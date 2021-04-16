package ru.diasoft.digitalq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * FinalDemoApplication
 *
 * @author Skyhunter
 * @date 16.04.2021
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableDiscoveryClient
@EnableCaching
@EnableJpaRepositories({"ru.diasoft.micro", "ru.diasoft.digitalq"})
@EntityScan({"ru.diasoft.micro", "ru.diasoft.digitalq"})
@ComponentScan({"ru.diasoft.micro", "ru.diasoft.digitalq"})
@SpringBootApplication
public class FinalDemoApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(FinalDemoApplication.class, args);
        } catch (Throwable ex) {
            if (!ex.getClass().getSimpleName().contains("SilentExitException")) {
                ex.printStackTrace();
            }
            throw ex;
        }
    }

}
