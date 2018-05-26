package de.hska.iwi.vs.lab.core.product.service;

import de.hska.iwi.vs.lab.core.product.entity.Product;
import de.hska.iwi.vs.lab.core.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
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
        } catch (Exception e) {
        }

        int category_id = 0;
        try {
            category_id = Integer.parseInt(searchPhrase);
        } catch (Exception e) {
        }

        return productRepository.findDistinctProductByNameContainingOrDetailsContainingOrPriceBetweenOrCategoryIdAllIgnoreCase(searchPhrase, searchPhrase, minPrice, maxPrice, category_id);
    }

    public Optional<Product> findById(int productId) {
        return productRepository.findById(productId);
    }

    public HttpStatus addProduct(Product product) {
        product.toString();
        if (validate(product)) {
            Product newProduct = new Product();
            newProduct.setName(product.getName());
            newProduct.setDetails(product.getDetails());
            newProduct.setPrice(product.getPrice());
            newProduct.setCategoryId(product.getCategoryId());
            productRepository.save(newProduct);

            return HttpStatus.CREATED;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    public HttpStatus deleteProduct(int productId) {
        productRepository.deleteById(productId);
        return HttpStatus.ACCEPTED;
    }

    private boolean validate(Product product) {
        return filterName()
                .and(filterDetails())
                .and(filterCategoryId())
                .and(filterPrice())
                .and(filterCategoryId())
                .test(product);
    }


    private Predicate<Product> filterName() {
        return product -> product.getName().length() > 0;
    }

    private Predicate<Product> filterDetails() {
        return product -> product.getDetails().length() > 0;
    }

    private Predicate<Product> filterPrice() {
        return product -> product.getPrice() > 0;
    }

    private Predicate<Product> filterCategoryId() {
        return product -> product.getCategoryId() > 0;
    }

}
