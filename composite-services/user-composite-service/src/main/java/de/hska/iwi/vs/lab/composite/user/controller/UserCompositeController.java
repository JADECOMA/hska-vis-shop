package de.hska.iwi.vs.lab.composite.user.controller;

import de.hska.iwi.vs.lab.composite.user.entity.User;
import de.hska.iwi.vs.lab.composite.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<User> getUserById(@PathVariable("userId") int userId) {
        log.info("COMPOSITE URL-PATH: /{userId} | METHOD: GET");

        return userService.findById(userId);
    }

    @GetMapping(value = "/checkLogin/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> findByUserName(@PathVariable("userName") String userName) {
        log.info("COMPOSITE URL-PATH: /checkLogin/{userName} | METHOD: GET");

        return userService.findByUsername(userName);
    }
}
