package de.hska.iwi.vs.lab.composite.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
//@EnableHystrixDashboard
//@RibbonClient("user-proxy")
public class ProductCompositeService {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ProductCompositeService.class, args);
    }
}