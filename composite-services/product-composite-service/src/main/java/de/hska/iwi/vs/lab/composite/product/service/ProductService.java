package de.hska.iwi.vs.lab.composite.product.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import de.hska.iwi.vs.lab.composite.product.entity.Category;
import de.hska.iwi.vs.lab.composite.product.entity.Product;
import de.hska.iwi.vs.lab.composite.product.entity.ProductFromCore;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        Category category = new Category();
        category.setName("dummy");
        category.setId(0);

        Product product = new Product();
        product.setName("No Products available for searchPhrase " + searchPhrase);
        product.setDetails("No details available");
        product.setPrice(0.00);
        product.setCategory(category);
        List<Product> list = Arrays.asList(product);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    /******************************************************************************************************************/
    @HystrixCommand(fallbackMethod = "findProductByIdFallback")
    public ResponseEntity<Product> findProductById(@PathVariable int productId) {
        URI uri = null;
        uri = URI.create("http://product-core-service:9000/view/" + productId);

        String productJson = restTemplate.getForObject(uri, String.class);
        JSONObject jsonProduct = new JSONObject(productJson);

        uri = URI.create("http://category-core-service:9020/get/" + jsonProduct.getInt("categoryId"));
        String categoryJson = restTemplate.getForObject(uri, String.class);

        JSONObject jsonCategory = new JSONObject(categoryJson);
        Category category = new Category();
        category.setId(jsonCategory.getInt("id"));
        category.setName(jsonCategory.getString("name"));

        Product product = new Product();
        product.setId(jsonProduct.getInt("id"));
        product.setName(jsonProduct.getString("name"));
        product.setDetails(jsonProduct.getString("details"));
        product.setPrice(jsonProduct.getDouble("price"));
        product.setCategory(category);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    public ResponseEntity<Product> findProductByIdFallback(@PathVariable int productId) {
        log.info("\t\tCOMPOSITE findProductByIdFallback | METHOD: GET");

        Product product = new Product();
        product.setName("No Products available for id " + productId);
        product.setDetails("No details available");
        product.setPrice(0.00);

        Category category = new Category();
        category.setId(0);
        category.setName("ZIRPINS");
        product.setCategory(category);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /******************************************************************************************************************/
    @HystrixCommand(fallbackMethod = "getProductsFallback")
    public ResponseEntity<List<Product>> getProducts() {
        URI uri = null;
        uri = URI.create("http://product-core-service:9000/get");
        String productJson = restTemplate.getForObject(uri, String.class);

        ObjectMapper mapper = new ObjectMapper();
        try {
            List<ProductFromCore> productCoreList = mapper.readValue(productJson, new TypeReference<List<ProductFromCore>>() {
            });

            List<Product> productList = new ArrayList<Product>();
            for (ProductFromCore p : productCoreList) {
                uri = URI.create("http://category-core-service:9020/get/" + p.getCategoryId());
                String categoryJson = restTemplate.getForObject(uri, String.class);

                JSONObject json = new JSONObject(categoryJson);
                Category category = new Category();

                category.setId(json.getInt("id"));
                category.setName(json.getString("name"));

                Product product = new Product();
                product.setId(p.getId());
                product.setName(p.getName());
                product.setDetails(p.getDetails());
                product.setPrice(p.getPrice());
                product.setCategory(category);
                productList.add(product);
            }

            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity<List<Product>> getProductsFallback() {
        log.info("\t\tCOMPOSITE getProductsFallback | METHOD: GET");

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
