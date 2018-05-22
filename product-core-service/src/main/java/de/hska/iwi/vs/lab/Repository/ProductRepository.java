package de.hska.iwi.vs.lab.Repository;

import de.hska.iwi.vs.lab.Entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findById(int id);
    List<Product> findDistinctProductByNameContainingOrDetailsContainingOrPriceBetweenOrCategoryIdAllIgnoreCase(String name, String details, double minPrice, double maxPrice, int category_id);
}