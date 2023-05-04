package com.ryan.employee;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

/**
 * @author ryan
 * @version Id: RestroomApplication, v 0.1 2023/4/27 2:36 PM ryan Exp $
 */
@EnableDiscoveryClient
@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = { "com.ryan" })
@EnableFeignClients(basePackages = { "com.ryan" })
public class EmployeeApplication {


    @Bean
    Logger.Level feignLoggerData() {
        return Logger.Level.FULL;
    }

    public static void main(String[] args) {
        SpringApplication.run(EmployeeApplication.class);
    }
}
