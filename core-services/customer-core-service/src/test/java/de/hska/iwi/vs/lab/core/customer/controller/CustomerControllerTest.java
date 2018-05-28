package de.hska.iwi.vs.lab.core.customer.controller;

import de.hska.iwi.vs.lab.core.customer.entity.Customer;
import de.hska.iwi.vs.lab.core.customer.entity.LoginData;
import de.hska.iwi.vs.lab.core.customer.service.CustomerService;
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
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Value("classpath:customer.json")
    private Resource json;

    private String name;
    private String lastName;
    private String userName;
    private String password;
    private int role;

    Customer customer() {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setLastname(lastName);
        customer.setUsername(userName);
        customer.setPassword(password);
        customer.setRole(role);
        return customer;
    }

    @Before
    public void setup() {
        name = "Name";
        lastName = "LastName";
        userName = "UserName";
        password = "Password";
        role = 1;
    }

    @Test
    public void givenCustomerWhenGetListCustomerThenReturnJson() throws Exception {
        Customer customer1 = customer();

        List<Customer> allProducts = Arrays.asList(customer1);
        given(customerService.listCustomer()).willReturn(allProducts);

        mockMvc.perform(get("/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(customer1.getName())))
                .andExpect(jsonPath("$[0].lastname", is(customer1.getLastname())))
                .andExpect(jsonPath("$[0].username", is(customer1.getUsername())))
                .andExpect(jsonPath("$[0].password", is(customer1.getPassword())))
                .andExpect(jsonPath("$[0].role", is(customer1.getRole())));
    }

    @Test
    public void logout() throws Exception {
        mockMvc.perform(get("/logout")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void login() throws Exception {
        when(customerService.login(any(LoginData.class))).thenReturn(HttpStatus.OK);
        String content = StreamUtils.copyToString(json.getInputStream(), Charset.forName("UTF-8"));
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk());
    }

    @Test
    public void addCustomer() throws Exception {
        when(customerService.addCustomer(any(Customer.class))).thenReturn(HttpStatus.CREATED);
        String content = StreamUtils.copyToString(json.getInputStream(), Charset.forName("UTF-8"));
        mockMvc.perform(put("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isCreated());
    }

    @Test
    public void addProductWithoutBody() throws Exception {
        mockMvc.perform(put("/")).andExpect(status().isBadRequest());
        verify(customerService, never()).addCustomer(any(Customer.class));
        assertTrue(true);
    }

    @Test
    public void whenGivenExistingCustomerIdReturnJson() throws Exception {
        Customer customer = customer();

        given(customerService.findById(1)).willReturn(Optional.of(customer));

        mockMvc.perform(get("/{customerId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(customer.getName())))
                .andExpect(jsonPath("$.lastname", is(customer.getLastname())))
                .andExpect(jsonPath("$.username", is(customer.getUsername())))
                .andExpect(jsonPath("$.password", is(customer.getPassword())))
                .andExpect(jsonPath("$.role", is(customer.getRole())));
    }
}
