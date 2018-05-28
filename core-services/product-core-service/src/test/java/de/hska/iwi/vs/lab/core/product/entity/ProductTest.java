package de.hska.iwi.vs.lab.core.product.entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@RunWith(MockitoJUnitRunner.class)
public class ProductTest {

    private String name;
    private String details;
    private double price;
    private int categoryId;

    Product product() {
        Product product = new Product();
        product.setName(name);
        product.setDetails(details);
        product.setPrice(price);
        product.setCategoryId(categoryId);
        return product;
    }

    @Before
    public void setup() {
        name = "Name";
        details = "Details";
        price = 41.11;
        categoryId = 1;
    }

    @Test
    public void getName() {
        assertThat(product().getName(), is(name));
    }

    @Test
    public void setName() {
        name = "Test";
        Product product = product();
        product.setName(name);
        assertThat(product().getName(), is(name));
    }

    @Test
    public void getDetails() {
        assertThat(product().getDetails(), is(details));
    }

    @Test
    public void setDetails() {
        details = "Test";
        Product product = product();
        product.setDetails(details);
        assertThat(product().getDetails(), is(details));
    }

    @Test
    public void getPrice() {
        assertThat(product().getPrice(), is(price));
    }

    @Test
    public void setPrice() {
        price = 15.5;
        Product product = product();
        product.setPrice(price);
        assertThat(product().getPrice(), is(price));
    }

    @Test
    public void getCategoryId() {
        assertThat(product().getCategoryId(), is(categoryId));
    }

    @Test
    public void setCategoryId() {
        categoryId = 2;
        Product product = product();
        product.setCategoryId(categoryId);
        assertThat(product().getCategoryId(), is(categoryId));
    }

    @Test
    public void stringify() {
        Product product = product();
        String output = "{" +
                "\"name\":\"Name\"," +
                "\"details\":\"Details\"," +
                "\"price\":\"41.11\"," +
                "\"category_id\":\"1\"" +
                "}";
        assertThat(product.toString(), is(output));
    }
}
