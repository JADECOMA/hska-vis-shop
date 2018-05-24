package de.hska.iwi.vs.lab.Controller;

import de.hska.iwi.vs.lab.Entity.Product;
import de.hska.iwi.vs.lab.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/search/{searchPhrase}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchProducts(@PathVariable String searchPhrase) {
        return new ResponseEntity<>(productService.searchProducts(searchPhrase), HttpStatus.OK);
    }

    @GetMapping(value = "/view/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable int productId) {
        return new ResponseEntity<>(productService.findById(productId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteProduct(@PathVariable int productId) {
        HttpStatus status = productService.deleteProduct(productId);
        return new ResponseEntity<>(status);
    }

    @PutMapping()
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        HttpStatus status = productService.addProduct(product);
        return new ResponseEntity<>(status);
    }

    @GetMapping(value = "/test/{testIt}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> ok(@PathVariable String testIt) {
        return new ResponseEntity<>(testIt, HttpStatus.OK);
    }
}