package de.hska.iwi.vs.lab.core.customer.controller;

import de.hska.iwi.vs.lab.core.customer.entity.Customer;
import de.hska.iwi.vs.lab.core.customer.entity.LoginData;
import de.hska.iwi.vs.lab.core.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    private static Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @PutMapping(value="/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        log.info("\tURL-PATH: / | METHOD: PUT");

        HttpStatus status = customerService.addCustomer(customer);
        return new ResponseEntity<>(status);
    }

    @GetMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listCustomer() {
        log.info("\tURL-PATH: / | METHOD: GET");

        return new ResponseEntity<>(customerService.listCustomer(), HttpStatus.OK);
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginData loginData) {
        log.info("\tURL-PATH: /login | METHOD: POST");

        return new ResponseEntity<>(customerService.login(loginData), HttpStatus.OK);
    }

    @GetMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> logout() {
        log.info("\tURL-PATH: /logout | METHOD: GET");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable int customerId) {
        log.info("\tURL-PATH: /{customerId} | METHOD: GET");

        return new ResponseEntity<>(customerService.findById(customerId), HttpStatus.OK);
    }
}
