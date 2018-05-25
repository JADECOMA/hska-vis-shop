package de.hska.iwi.vs.lab.core.category.controller;

import de.hska.iwi.vs.lab.core.category.entity.Category;
import de.hska.iwi.vs.lab.core.category.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    private static Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        log.info("URL-PATH: / | METHOD: PUT");

        HttpStatus status = categoryService.addCategory(category);
        return new ResponseEntity<>(status);
    }

    @DeleteMapping(value = "/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCategory(@PathVariable int categoryId) {
        log.info("URL-PATH: /{categoryId} | METHOD: DELETE");

        HttpStatus status = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(status);
    }

    @GetMapping(value = "/test/{testIt}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> ok(@PathVariable String testIt) {
        log.info("URL-PATH: /test/{testIt} | METHOD: GET");
        return new ResponseEntity<>("{\"value\":\"" + testIt + "\"}", HttpStatus.OK);
    }
}
