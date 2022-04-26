package com.isaias.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroGatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroGatewayServerApplication.class, args);
    }

}
