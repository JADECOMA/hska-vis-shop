package de.hska.iwi.vs.lab.composite.portfolio.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import de.hska.iwi.vs.lab.composite.portfolio.entity.Category;
import de.hska.iwi.vs.lab.composite.portfolio.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

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
        URI uri = URI.create("http://product-core-service:9000/");

        return new ResponseEntity<>(restTemplate.getForObject(uri, HttpStatus.class), HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> addProductFallback(@RequestBody Product product) {
        log.info("\t\tCOMPOSITE addProductFallback | METHOD: PUT");

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /******************************************************************************************************************/
    @HystrixCommand(fallbackMethod = "addCategoryFallback")
    public ResponseEntity<HttpStatus> addCategory(@RequestBody Category category) {
        log.info("INCREDIBLE IS WORKING - PERHAPS");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Category> request = new HttpEntity<>(category);
        ResponseEntity<Category> response = restTemplate
                .exchange("http://category-core-service:9020/", HttpMethod.PUT, request, Category.class);

        log.info("INCREDIBLE IS WORKING - !!!!!!!!!!!!!!!11!1!11111");
//        URI uri = URI.create("http://category-core-service:9020/");
//        restTemplate.delete(uri);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> addCategoryFallback(@RequestBody Product product) {
        log.info("\t\tCOMPOSITE addCategoryFallback | METHOD: PUT");

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /******************************************************************************************************************/
    @HystrixCommand(fallbackMethod = "deleteCategoryFallback")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable int categoryId) {
        URI uri = URI.create("http://category-core-service:9020/" + categoryId);

        return new ResponseEntity<>(restTemplate.getForObject(uri, HttpStatus.class), HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteCategoryFallback(@RequestBody Product product) {
        log.info("\t\tCOMPOSITE deleteCategoryFallback | METHOD: PUT");

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /******************************************************************************************************************/
    @HystrixCommand(fallbackMethod = "getCategoriesFallback")
    public ResponseEntity<Iterable<Category>> getCategories() {
        URI uri = URI.create("http://category-core-service:9020/get");

        return new ResponseEntity<>(restTemplate.getForObject(uri, Iterable.class), HttpStatus.OK);
    }

    public ResponseEntity<Iterable<Category>> getCategoriesFallback() {
        log.info("\t\tCOMPOSITE getCategoriesFallback | METHOD: GET");

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

    /******************************************************************************************************************/
    @HystrixCommand(fallbackMethod = "genericGetFallback")
    public ResponseEntity<String> genericGet() {
        URI uri = URI.create("http://product-core-service:9000/view/1");

        return new ResponseEntity<>(restTemplate.getForObject(uri, String.class), HttpStatus.OK);
    }

    public ResponseEntity<String> genericGetFallback() {
        log.info("\t\tCOMPOSITE getProductsFallback | METHOD: GET");

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
