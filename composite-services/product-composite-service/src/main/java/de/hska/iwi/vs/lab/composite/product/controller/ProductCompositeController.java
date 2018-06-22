package de.hska.iwi.vs.lab.composite.product.controller;

import de.hska.iwi.vs.lab.composite.product.entity.Product;
import de.hska.iwi.vs.lab.composite.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@EnableCircuitBreaker
public class ProductCompositeController {

    private static Logger log = LoggerFactory.getLogger(ProductCompositeController.class);

    @Autowired
    private ProductService productService;

    @Bean
    public RestTemplate rest(RestTemplateBuilder builder) {
        return builder.build();
    }

    @GetMapping(value = "/search/{searchPhrase}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> searchProducts(@PathVariable String searchPhrase) {
        log.info("COMPOSITE URL-PATH: /search/{searchPhrase} | METHOD: GET");

        return productService.searchProducts(searchPhrase);
    }

    @GetMapping(value = "/view/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findProductById(@PathVariable int productId) {
        log.info("COMPOSITE URL-PATH:/view/{productId} | METHOD: GET");

        return productService.findProductById(productId);
    }


    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProducts() {
        log.info("COMPOSITE URL-PATH: /get | METHOD: GET");

        return productService.getProducts();
    }
}
