package de.hska.iwi.vs.lab.Controller;

import de.hska.iwi.vs.lab.Entity.Product;
import de.hska.iwi.vs.lab.Service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StreamUtils;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Value("classpath:product.json")
    private Resource json;

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
    public void givenProductsWhenGetSearchSearchPhraseThenReturnJson() throws Exception {
        Product product1 = product();

        List<Product> allProducts = Arrays.asList(product1);
        given(productService.searchProducts("1")).willReturn(allProducts);

        mockMvc.perform(get("/search/{searchPhrase}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(product1.getName())))
                .andExpect(jsonPath("$[0].details", is(product1.getDetails())))
                .andExpect(jsonPath("$[0].price", is(product1.getPrice())))
                .andExpect(jsonPath("$[0].categoryId", is(product1.getCategoryId())));
    }

    @Test
    public void givenProductWhenGetViewIdThenReturnJsonArray() throws Exception {
        Product product1 = product();

        given(productService.findById(1)).willReturn(product1);

        mockMvc.perform(get("/view/{productId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(product1.getName())));
    }

    @Test
    public void deleteProduct() throws Exception {
        given(productService.deleteProduct(1)).willReturn(HttpStatus.ACCEPTED);

        mockMvc.perform(delete("/{productId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void addProduct() throws Exception {
        when(productService.addProduct(any(Product.class))).thenReturn(HttpStatus.CREATED);
        String content = StreamUtils.copyToString(json.getInputStream(), Charset.forName("UTF-8"));
        mockMvc.perform(put("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isCreated());
    }

    @Test
    public void addProductWithoutBody() throws Exception {
        mockMvc.perform(put("/")).andExpect(status().isBadRequest());
        verify(productService, never()).addProduct(any(Product.class));
        assertTrue(true);
    }
}
