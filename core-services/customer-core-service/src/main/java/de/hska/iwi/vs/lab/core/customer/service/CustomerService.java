package de.hska.iwi.vs.lab.core.customer.service;

import de.hska.iwi.vs.lab.core.customer.entity.Customer;
import de.hska.iwi.vs.lab.core.customer.entity.Role;
import de.hska.iwi.vs.lab.core.customer.repository.CustomerRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Predicate;

@Component
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public HttpStatus addCustomer(String customerString) {
        Role role = new Role();
        role.setId(2);
        role.setLevel(1);
        role.setTyp("user");

        JSONObject json = new JSONObject(customerString);
        Customer customer = new Customer();

        customer.setPassword(json.getString("password"));
        customer.setFirstname(json.getString("name"));
        customer.setLastname(json.getString("lastname"));
        customer.setUsername(json.getString("username"));
        customer.setRole(role);

        if (validate(customer)) {
            customerRepository.save(customer);

            return HttpStatus.CREATED;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    public Optional<Customer> findById(int customerId) {
        return customerRepository.findById(customerId);
    }

    public Optional<Customer> findByUsername(String userName) {
        return customerRepository.findByUsernameIgnoreCase(userName);
    }

    private boolean validate(Customer customer) {
        return filterName()
                .and(filterLastName())
                .and(filterPassword())
                .and(filterUsername())
                .test(customer);
    }


    private Predicate<Customer> filterName() {
        return customer -> customer.getFirstname().length() > 0;
    }

    private Predicate<Customer> filterLastName() {
        return customer -> customer.getLastname().length() > 0;
    }

    private Predicate<Customer> filterUsername() {
        return customer -> customer.getUsername().length() > 0;
    }

    private Predicate<Customer> filterPassword() {
        return customer -> customer.getPassword().length() > 0;
    }

}
