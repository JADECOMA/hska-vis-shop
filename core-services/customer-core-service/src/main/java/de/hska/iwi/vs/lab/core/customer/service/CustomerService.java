package de.hska.iwi.vs.lab.core.customer.service;

import de.hska.iwi.vs.lab.core.customer.entity.Customer;
import de.hska.iwi.vs.lab.core.customer.entity.LoginData;
import de.hska.iwi.vs.lab.core.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@Component
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public HttpStatus addCustomer(Customer customer) {
        if (validate(customer)) {
            Customer newCustomer = new Customer();
            newCustomer.setName(customer.getName());
            newCustomer.setLastname(customer.getLastname());
            newCustomer.setPassword(customer.getPassword());
            newCustomer.setUsername(customer.getUsername());
            newCustomer.setRole(1);
            customerRepository.save(newCustomer);

            return HttpStatus.CREATED;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    public HttpStatus login(LoginData loginData) {
        Objects.requireNonNull(loginData, "loginData data cannot be empty.");

        Customer customer = customerRepository.findByUsernameIgnoreCase(loginData.getUsername());

        if (customer.getPassword().equals(loginData.getPassword())) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.FORBIDDEN;
        }
    }

    public Iterable<Customer> listCustomer() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findById(int customerId) {
        return customerRepository.findById(customerId);
    }

    private boolean validate(Customer customer) {
        return filterName()
                .and(filterLastName())
                .and(filterPassword())
                .and(filterUsername())
                .test(customer);
    }


    private Predicate<Customer> filterName() {
        return customer -> customer.getName().length() > 0;
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
