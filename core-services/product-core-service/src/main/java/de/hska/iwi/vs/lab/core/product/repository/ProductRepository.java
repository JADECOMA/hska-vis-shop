package de.hska.iwi.vs.lab.core.product.repository;

import de.hska.iwi.vs.lab.core.product.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> findProductByNameContainingAndPriceBetweenAllIgnoreCase(String name, double minPrice, double maxPrice);
    List<Product> findByCategoryId(int categoryId);
    List<Product> findAll();
}