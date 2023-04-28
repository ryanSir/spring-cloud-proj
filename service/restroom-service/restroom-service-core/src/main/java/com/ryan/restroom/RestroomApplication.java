package com.ryan.restroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author ryan
 * @version Id: RestroomApplication, v 0.1 2023/4/27 2:36 PM ryan Exp $
 */
@EnableDiscoveryClient
@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = { "com.ryan" })
public class RestroomApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestroomApplication.class);
    }
}
