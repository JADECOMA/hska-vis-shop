package de.hska.iwi.vs.lab.core.category.service;

import de.hska.iwi.vs.lab.core.category.entity.Category;
import de.hska.iwi.vs.lab.core.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public HttpStatus addCategory(Category category) {
        if (validate(category)) {
            Category newCategory = new Category();
            newCategory.setName(category.getName());
            categoryRepository.save(newCategory);

            return HttpStatus.CREATED;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }
    public HttpStatus deleteCategory(int categoryId) {
        categoryRepository.deleteById(categoryId);
        return HttpStatus.ACCEPTED;
    }

    public Iterable<Category> getCategories() {
        return categoryRepository.findAll();
    }

    private boolean validate(Category category) {
        return filterName()
                .test(category);
    }


    private Predicate<Category> filterName() {
        return category -> category.getName().length() > 0;
    }
}
