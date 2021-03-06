package de.hska.iwi.vs.lab.core.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@EnableDiscoveryClient
public class CustomerCoreService {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(CustomerCoreService.class, args);
    }
}