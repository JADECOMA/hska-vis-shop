package de.hska.iwi.vs.lab.core.product.controller;

import de.hska.iwi.vs.lab.core.product.entity.Product;
import de.hska.iwi.vs.lab.core.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private static Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/search/{searchPhrase}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchProducts(@PathVariable String searchPhrase) {
        log.info("\tURL-PATH: /search/{searchPhrase} | METHOD: GET");

        return new ResponseEntity<>(productService.searchProducts(searchPhrase), HttpStatus.OK);
    }

    @GetMapping(value = "/view/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable int productId) {
        log.info("\tURL-PATH: /view/{productId} | METHOD: GET");

        return new ResponseEntity<>(productService.findById(productId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteProduct(@PathVariable int productId) {
        log.info("\tURL-PATH: /{productId} | METHOD: DELETE");

        return new ResponseEntity<>( productService.deleteProduct(productId));
    }

    @PutMapping()
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        log.info("\tURL-PATH: / | METHOD: PUT");

        return new ResponseEntity<>(productService.addProduct(product));
    }
}