package de.hska.iwi.vs.lab.core.product.repository;

import de.hska.iwi.vs.lab.core.product.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> findDistinctProductByNameContainingOrDetailsContainingOrPriceBetweenOrCategoryIdAllIgnoreCase(String name, String details, double minPrice, double maxPrice, int category_id);
    List<Product> findByCategoryId(int categoryId);
}