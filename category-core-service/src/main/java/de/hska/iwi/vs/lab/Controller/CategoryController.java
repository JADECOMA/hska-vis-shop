package de.hska.iwi.vs.lab.Controller;

import de.hska.iwi.vs.lab.Entity.Category;
import de.hska.iwi.vs.lab.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        HttpStatus status = categoryService.addCategory(category);
        return new ResponseEntity<>(status);
    }

    @DeleteMapping(value = "/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCategory(@PathVariable int categoryId) {
        HttpStatus status = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(status);
    }

    @GetMapping(value = "/test/{testIt}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> ok(@PathVariable String testIt) {
        return new ResponseEntity<>(testIt, HttpStatus.OK);
    }
}
