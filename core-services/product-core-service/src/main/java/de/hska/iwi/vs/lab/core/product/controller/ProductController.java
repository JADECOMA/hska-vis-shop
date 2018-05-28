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

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private static Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/search/{searchPhrase}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> searchProducts(@PathVariable String searchPhrase) {
        log.info("\tURL-PATH: /search/{searchPhrase} | METHOD: GET");

        List<Product> results = productService.searchProducts(searchPhrase);
        if (results.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(results, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/view/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Product>> findById(@PathVariable int productId) {
        log.info("\tURL-PATH: /view/{productId} | METHOD: GET");

        return new ResponseEntity<>(productService.findById(productId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable int productId) {
        log.info("\tURL-PATH: /{productId} | METHOD: DELETE");

        return new ResponseEntity<>( productService.deleteProduct(productId));
    }

    @GetMapping(value = "/byCategory/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Product>> findByCategoryId(@PathVariable int categoryId) {
        log.info("\tURL-PATH: /view/{productId} | METHOD: GET");

        return new ResponseEntity<>(productService.findByCategoryId(categoryId), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<HttpStatus> addProduct(@RequestBody Product product) {
        log.info("\tURL-PATH: / | METHOD: PUT");

        return new ResponseEntity<>(productService.addProduct(product));
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Product>> getProducts() {
        log.info("\tURL-PATH: /get | METHOD: GET");

        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }
}