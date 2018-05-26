package de.hska.iwi.vs.lab.composite.product.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import de.hska.iwi.vs.lab.composite.product.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class ProductService {

    private static Logger log = LoggerFactory.getLogger(ProductService.class);

    private final RestTemplate restTemplate;

    public ProductService(RestTemplate rest) {
        this.restTemplate = rest;
    }

    @HystrixCommand(fallbackMethod = "searchProductsFallback")
    public ResponseEntity<List<Product>> searchProducts(@PathVariable String searchPhrase) {
        URI uri = URI.create("http://product-core-service:9000/search/" + searchPhrase);

        return new ResponseEntity<>(restTemplate.getForObject(uri, List.class), HttpStatus.OK);
    }

    public ResponseEntity<List<Product>> searchProductsFallback(@PathVariable String searchPhrase) {
        log.info("\t\tCOMPOSITE searchProductsFallback | METHOD: GET");

        Product product = new Product();
        product.setName("No Products available for searchPhrase " + searchPhrase);
        product.setDetails("No details available");
        product.setPrice(0.00);
        product.setCategoryId(0);
        List<Product> list = Arrays.asList(product);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    /******************************************************************************************************************/
    @HystrixCommand(fallbackMethod = "findProductByIdFallback")
    public ResponseEntity<Optional<Product>> findProductById(@PathVariable int productId) {
        URI uri = URI.create("http://product-core-service:9000/view/" + productId);

        return new ResponseEntity<>(restTemplate.getForObject(uri, Optional.class), HttpStatus.OK);
    }

    public ResponseEntity<Optional<Product>> findProductByIdFallback(@PathVariable int productId) {
        log.info("\t\tCOMPOSITE findProductByIdFallback | METHOD: GET");

        Product product = new Product();
        product.setName("No Products available for id " + productId);
        product.setDetails("No details available");
        product.setPrice(0.00);
        product.setCategoryId(0);
        return new ResponseEntity<>(Optional.of(product), HttpStatus.OK);
    }
}
