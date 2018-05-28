package de.hska.iwi.vs.lab.composite.portfolio.service;

import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import de.hska.iwi.vs.lab.composite.portfolio.entity.Category;
import de.hska.iwi.vs.lab.composite.portfolio.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Optional;

@Component
public class PortfolioService {

    private static Logger log = LoggerFactory.getLogger(PortfolioService.class);

    private final RestTemplate restTemplate;

    public PortfolioService(RestTemplate rest) {
        this.restTemplate = rest;
    }

    /******************************************************************************************************************/
    @HystrixCommand(fallbackMethod = "deleteProductFallback")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable int productId) {
        URI uri = URI.create("http://product-core-service:9000/" + productId);
        restTemplate.delete(uri);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteProductFallback(@PathVariable int productId) {
        log.info("\t\tCOMPOSITE deleteProductFallback | METHOD: DELETE");

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /******************************************************************************************************************/
    @HystrixCommand(fallbackMethod = "addProductFallback")
    public ResponseEntity<HttpStatus> addProduct(@RequestBody Product product) {
        URI uri = URI.create("http://category-core-service:9020/get/" + product.getCategoryId());

        Category category = (this.restTemplate.getForObject(uri, Category.class));

        if (category.getId() == product.getCategoryId()) {
            String requestJson = product.toString();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
            restTemplate.put("http://product-core-service:9000/", entity);

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            log.info("CategoryIds are different");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<HttpStatus> addProductFallback(@RequestBody Product product) {
        log.info("\t\tCOMPOSITE addProductFallback | METHOD: PUT");

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /******************************************************************************************************************/
    @HystrixCommand(fallbackMethod = "addCategoryFallback")
    public ResponseEntity<HttpStatus> addCategory(@RequestBody Category category) {
        String requestJson = category.toString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        restTemplate.put("http://category-core-service:9020/", entity);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> addCategoryFallback(@RequestBody Category category) {
        log.info("\t\tCOMPOSITE addCategoryFallback | METHOD: PUT");

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /******************************************************************************************************************/
    @HystrixCommand(fallbackMethod = "deleteCategoryFallback")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable int categoryId) {
        URI uri;
        uri = URI.create("http://product-core-service:9000/byCategory/" + categoryId);

        Iterable<Product> products = this.restTemplate.getForObject(uri, Iterable.class);
        int size = Lists.newArrayList(products).size();

        if (size == 0) {
            uri = URI.create("http://category-core-service:9020/" + categoryId);
            restTemplate.delete(uri);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<HttpStatus> deleteCategoryFallback(@PathVariable int categoryId) {
        log.info("\t\tCOMPOSITE deleteCategoryFallback | METHOD: DELETE");

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /******************************************************************************************************************/
    @HystrixCommand(fallbackMethod = "getCategoryByIdFallback")
    public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable int categoryId) {
        URI uri = URI.create("http://category-core-service:9020/get/" + categoryId);

        return new ResponseEntity<>(restTemplate.getForObject(uri, Optional.class), HttpStatus.OK);
    }

    public ResponseEntity<Optional<Category>> getCategoryByIdFallback(@PathVariable int categoryId) {
        log.info("\t\tCOMPOSITE getCategoryByIdFallback | METHOD: GET");

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /******************************************************************************************************************/
    @HystrixCommand(fallbackMethod = "getProductsFallback")
    public ResponseEntity<Iterable<Category>> getProducts() {
        URI uri = URI.create("http://product-core-service:9000/get");

        return new ResponseEntity<>(restTemplate.getForObject(uri, Iterable.class), HttpStatus.OK);
    }

    public ResponseEntity<Iterable<Category>> getProductsFallback() {
        log.info("\t\tCOMPOSITE getProductsFallback | METHOD: GET");

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
