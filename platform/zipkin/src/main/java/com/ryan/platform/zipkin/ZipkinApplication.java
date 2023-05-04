package com.ryan.platform.zipkin;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.EnableZipkinServer;

/**
 * @author ryan
 * @version Id: ZipkinApplication, v 0.1 2023/5/4 2:23 PM ryan Exp $
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinServer
public class ZipkinApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ZipkinApplication.class).web(WebApplicationType.SERVLET).run();
    }
}
