package com.example.restservice;

import com.example.restservice.kafka.ConsumerChannel;
import com.example.restservice.kafka.ProducerChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Main class
 *
 * @author Skyhunter
 * @date 26.02.2021
 */
@SpringBootApplication
@EnableSwagger2
@EnableBinding({ConsumerChannel.class, ProducerChannel.class})
public class RestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
    }
}
