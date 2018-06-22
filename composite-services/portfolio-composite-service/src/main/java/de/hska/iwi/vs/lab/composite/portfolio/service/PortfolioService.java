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
import java.util.List;

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
    public ResponseEntity<HttpStatus> addProduct(@RequestBody String product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(product, headers);
        restTemplate.put("http://product-core-service:9000/", entity);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> addProductFallback(@RequestBody String product) {
        log.info("\t\tCOMPOSITE addProductFallback | METHOD: PUT");

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /******************************************************************************************************************/
    @HystrixCommand(fallbackMethod = "addCategoryFallback")
    public ResponseEntity<HttpStatus> addCategory(@RequestBody String category) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(category, headers);
        restTemplate.put("http://category-core-service:9020/", entity);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> addCategoryFallback(@RequestBody String category) {
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
    @HystrixCommand(fallbackMethod = "getCategoriesFallback")
    public ResponseEntity<List<Category>> getCategories() {
        URI uri = URI.create("http://category-core-service:9020/get/");

        return new ResponseEntity<>(restTemplate.getForObject(uri, List.class), HttpStatus.OK);
    }

    public ResponseEntity<List<Category>> getCategoriesFallback() {
        log.info("\t\tCOMPOSITE getCategoriesFallback | METHOD: GET");

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
