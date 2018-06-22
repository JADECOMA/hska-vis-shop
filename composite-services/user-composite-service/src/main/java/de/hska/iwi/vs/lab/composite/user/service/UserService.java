package de.hska.iwi.vs.lab.composite.user.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import de.hska.iwi.vs.lab.composite.user.entity.Customer;
import de.hska.iwi.vs.lab.composite.user.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class UserService {

    private static Logger log = LoggerFactory.getLogger(UserService.class);

    private final RestTemplate restTemplate;

    public UserService(RestTemplate rest) {
        this.restTemplate = rest;
    }

    @HystrixCommand(fallbackMethod = "reliable")
    public ResponseEntity<Customer> findById(@PathVariable("userId") int userId) {
        URI uri = URI.create("http://customer-core-service:9010/" + userId);

        return new ResponseEntity<>(this.restTemplate.getForObject(uri, Customer.class), HttpStatus.OK);
    }

    public ResponseEntity<Customer> reliable(@PathVariable("userId") int userId) {
        log.info("COMPOSITE reliable | METHOD: GET");

        Customer customer = new Customer();
        customer.setFirstname("Name");
        customer.setLastname("LastName");
        customer.setPassword("***SECRET***");
        customer.setUsername("Super-User");
        customer.setRole(new Role("user", 1));
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @HystrixCommand(fallbackMethod = "findByUsernameFallback")
    public ResponseEntity<Customer> findByUsername(@PathVariable("userName") String userName) {
        URI uri = URI.create("http://customer-core-service:9010/checkLogin/" + userName);

        return new ResponseEntity<>(this.restTemplate.getForObject(uri, Customer.class), HttpStatus.OK);
    }

    public ResponseEntity<Customer> findByUsernameFallback(@PathVariable("userName") String userName) {
        log.info("COMPOSITE findByUsernameFallback | METHOD: GET");

        Customer customer = new Customer();
        customer.setFirstname("Name");
        customer.setLastname("LastName");
        customer.setPassword("***SECRET***");
        customer.setUsername("Super-User");
        customer.setRole(new Role("user", 1));
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @HystrixCommand(fallbackMethod = "addCustomerFallback")
    public ResponseEntity<Customer> addCustomer(@RequestBody String customer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(customer, headers);
        restTemplate.put("http://customer-core-service:9010/", customer);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Customer> addCustomerFallback(@RequestBody String customer) {
        log.info("\t\tCOMPOSITE addCustomerFallback | METHOD: PUT");

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
