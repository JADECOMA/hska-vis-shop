package de.hska.iwi.vs.lab.composite.portfolio.controller;

import de.hska.iwi.vs.lab.composite.portfolio.entity.Category;
import de.hska.iwi.vs.lab.composite.portfolio.service.PortfolioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@EnableCircuitBreaker
public class PortfolioCompositeController {

    private static Logger log = LoggerFactory.getLogger(PortfolioCompositeController.class);

    @Autowired
    private PortfolioService portfolioService;

    @Bean
    public RestTemplate rest(RestTemplateBuilder builder) {
        return builder.build();
    }

    /******************************************************************************************************************/
    @DeleteMapping(value = "/products/{productId}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable int productId) {
        log.info("COMPOSITE URL-PATH: /{categoryId} | METHOD: DELETE");

        return portfolioService.deleteProduct(productId);
    }

    @PutMapping(value = "/products")
    public ResponseEntity<HttpStatus> addProduct(@RequestBody String product) {
        log.info("COMPOSITE URL-PATH: / | METHOD: PUT");

        return portfolioService.addProduct(product);
    }

    /******************************************************************************************************************/
    @PutMapping(value = "/categories")
    public ResponseEntity<HttpStatus> addCategory(@RequestBody String category) {
        log.info("COMPOSITE URL-PATH: / | METHOD: PUT");

        return portfolioService.addCategory(category);
    }

    @DeleteMapping(value = "/categories/{categoryId}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable int categoryId) {
        log.info("COMPOSITE URL-PATH: /{categoryId} | METHOD: DELETE");

        return portfolioService.deleteCategory(categoryId);
    }

    @GetMapping(value = "/categories/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Category>> getCategoryById() {
        log.info("COMPOSITE URL-PATH: /get | METHOD: GET");

        return portfolioService.getCategories();
    }
}
