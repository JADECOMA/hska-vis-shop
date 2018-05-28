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

import java.util.Optional;

@RestController
public class CategoryController {

    private static Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> addCategory(@RequestBody Category category) {
        log.info("\tURL-PATH: / | METHOD: PUT");

        HttpStatus status = categoryService.addCategory(category);
        return new ResponseEntity<>(status);
    }

    @DeleteMapping(value = "/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable int categoryId) {
        log.info("\tURL-PATH: /{categoryId} | METHOD: DELETE");

        HttpStatus status = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(status);
    }

    @GetMapping(value = "/get/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable int categoryId) {
        log.info("\tURL-PATH: /{categoryId} | METHOD: GET");

        return new ResponseEntity<>(categoryService.findCategoryById(categoryId), HttpStatus.OK);
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Category>> getCategories() {
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
    }
}
