package de.hska.iwi.vs.lab.composite.user.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import de.hska.iwi.vs.lab.composite.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class UserService {

    private static Logger log = LoggerFactory.getLogger(UserService.class);

//    @Autowired
    private final RestTemplate restTemplate;

    public UserService(RestTemplate rest) {
        this.restTemplate = rest;
    }

    @HystrixCommand(fallbackMethod = "reliable")
    public ResponseEntity<User> findById(@PathVariable("userId") int userId) {
        log.info("COMPOSITE findById | METHOD: GET");

//        URI uri = URI.create("http://localhost:9090/api/customers/" + userId);
        URI uri = URI.create("http://customer-core-service:9010/" + userId);

        return new ResponseEntity<>(this.restTemplate.getForObject(uri, User.class), HttpStatus.OK);
    }

    public ResponseEntity<User> reliable(@PathVariable("userId") int userId) {
        log.info("COMPOSITE reliable | METHOD: GET");

        User user = new User();
        user.setName("Name");
        user.setLastname("LastName");
        user.setPassword("***SECRET***");
        user.setUsername("Super-User");
        user.setRole(1);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
