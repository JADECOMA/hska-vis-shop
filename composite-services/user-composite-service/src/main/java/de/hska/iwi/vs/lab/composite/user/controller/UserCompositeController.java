package de.hska.iwi.vs.lab.composite.user.controller;

import de.hska.iwi.vs.lab.composite.user.entity.Customer;
import de.hska.iwi.vs.lab.composite.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableCircuitBreaker
public class UserCompositeController {

    private static Logger log = LoggerFactory.getLogger(UserCompositeController.class);

    @Autowired
    private UserService userService;

    @Bean
    public RestTemplate rest(RestTemplateBuilder builder) {
        return builder.build();
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getUserById(@PathVariable("userId") int userId) {
        log.info("COMPOSITE URL-PATH: /{userId} | METHOD: GET");

        return userService.findById(userId);
    }

    @GetMapping(value = "/checkLogin/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> findByUserName(@PathVariable("userName") String userName) {
        log.info("COMPOSITE URL-PATH: /checkLogin/{userName} | METHOD: GET");

        return userService.findByUsername(userName);
    }

    @PutMapping(value = "/addCustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody String customer) {
        log.info("COMPOSITE URL-PATH: /addCustomer | METHOD: PUT");

        return userService.addCustomer(customer);
    }
}
