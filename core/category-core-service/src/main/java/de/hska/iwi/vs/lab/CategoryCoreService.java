package de.hska.iwi.vs.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@EnableDiscoveryClient
public class CategoryCoreService {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(CategoryCoreService.class, args);
    }
}