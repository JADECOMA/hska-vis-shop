package de.hska.iwi.vs.lab.core.customer.controller;

import de.hska.iwi.vs.lab.core.customer.entity.Customer;
import de.hska.iwi.vs.lab.core.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CustomerController {

    private static Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @PutMapping(value = "/")
    public ResponseEntity<HttpStatus> addCustomer(@RequestBody String customer) {
        log.info("\tURL-PATH: / | METHOD: PUT");

        log.info(customer);

        return new ResponseEntity<>(customerService.addCustomer(customer));
    }

    @GetMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> logout() {
        log.info("\tURL-PATH: /logout | METHOD: GET");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Customer>> findById(@PathVariable int customerId) {
        log.info("\tURL-PATH: /{customerId} | METHOD: GET");

        return new ResponseEntity<>(customerService.findById(customerId), HttpStatus.OK);
    }


    @GetMapping(value = "/checkLogin/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Customer>> findByUserName(@PathVariable String userName) {
        log.info("\tURL-PATH: /checkLogin/{userName} | METHOD: GET");

        return new ResponseEntity<>(customerService.findByUsername(userName), HttpStatus.OK);
    }
}
