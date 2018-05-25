package de.hska.iwi.vs.lab.core.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@EnableDiscoveryClient
public class ProductCoreService {
    public static void main(String[] args) {
        SpringApplication.run(ProductCoreService.class, args);
    }
}