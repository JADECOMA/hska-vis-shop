package de.hska.iwi.vs.lab.core.product.service;

import de.hska.iwi.vs.lab.core.product.entity.Product;
import de.hska.iwi.vs.lab.core.product.repository.ProductRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class ProductService {

    private static Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public List<Product> searchProducts(String searchPhrase) {
        String[] split = searchPhrase.split("&");
        String searchText = split[0];
        double minPrice;
        double maxPrice;

        if (split[1].equals("*")) {
            minPrice = 0.0;
        } else {
            minPrice = Double.parseDouble(split[1]);
        }

        if (split[2].equals("*")) {
            maxPrice = Double.MAX_VALUE;
        } else {
            maxPrice = Double.parseDouble(split[2]);
        }

        return productRepository.findProductByNameContainingAndPriceBetweenAllIgnoreCase(
                searchText,
                minPrice,
                maxPrice
        );
    }

    public Optional<Product> findById(int productId) {
        return productRepository.findById(productId);
    }

    public Iterable<Product> findByCategoryId (int categoryId) {
     return productRepository.findByCategoryId(categoryId);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public HttpStatus addProduct(String productString) {
        JSONObject json = new JSONObject(productString);

        Product product = new Product();
        product.setName(json.getString("name"));
        product.setDetails(json.getString("details"));
        product.setPrice(json.getDouble("price"));
        product.setCategoryId(json.getInt("categoryId"));

        if (validate(product)) {
            productRepository.save(product);

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
