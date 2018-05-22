package de.hska.iwi.vs.lab.Service;

import de.hska.iwi.vs.lab.Entity.Product;
import de.hska.iwi.vs.lab.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> searchProducts(String searchPhrase) {
        double price = 0.0;
        double minPrice = 0.0;
        double maxPrice = 0.0;
        try {
            price = Double.parseDouble(searchPhrase);
            minPrice = price - 0.5;
            maxPrice = price + 0.5;
        } catch (Exception e){}

        int category_id = 0;
        try {
            category_id = Integer.parseInt(searchPhrase);
        } catch (Exception e){}

        return productRepository.findDistinctProductByNameContainingOrDetailsContainingOrPriceBetweenOrCategoryIdAllIgnoreCase(searchPhrase, searchPhrase, minPrice, maxPrice, category_id);
    }

    public Product findById(int productId) {
        return productRepository.findById(productId);
    }

}
