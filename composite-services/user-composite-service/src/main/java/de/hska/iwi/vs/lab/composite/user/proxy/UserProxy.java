package de.hska.iwi.vs.lab.composite.user.proxy;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import de.hska.iwi.vs.lab.composite.user.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class UserProxy {

    private final Map<Long, User> userCache = new LinkedHashMap<Long, User>();

//    @Bean
//    RestTemplate restTemplate(){
//        return new RestTemplate();
//    }
//
//    @Autowired
//    private RestTemplate restTemplate;
//

    @HystrixCommand(fallbackMethod = "blubCache")
    //, commandProperties = {@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public ResponseEntity<?> findById(@PathVariable int customerId) {
//        User tmpuser =
//                restTemplate.getForObject("http://localhost:9090/api/customers/" + userId, User.class);
//        userCache.putIfAbsent(userId, tmpuser);
//        return tmpuser;
        User user = new RestTemplate().getForObject("http://localhost:9090/api/customers/{customerId}", User.class, customerId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<?> blubCache(@PathVariable int customerId) {
        User user = new User();
        user.setName("Name");
        user.setLastname("LastName");
        user.setPassword("***SECRET***");
        user.setUsername("Super-User");
        user.setRole(1);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}