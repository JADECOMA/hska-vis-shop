package de.hska.iwi.vs.lab.Controller;

import de.hska.iwi.vs.lab.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    @ResponseBody
    public HttpStatus testIt() {
        return HttpStatus.OK;
    }

    @GetMapping("/search/{searchPhrase}")
    public ResponseEntity<?> searchProducts(@PathVariable String searchPhrase) {
        return new ResponseEntity<>(productService.searchProducts(searchPhrase), HttpStatus.OK);
    }

    @GetMapping("/view/{productId}")
    public ResponseEntity<?> findById(@PathVariable int productId) {
        return new ResponseEntity<>(productService.findById(productId), HttpStatus.OK);
    }

    @RequestMapping("/test/{testIt}")
    public String ok(@PathVariable String testIt) {
        return testIt;
    }
}