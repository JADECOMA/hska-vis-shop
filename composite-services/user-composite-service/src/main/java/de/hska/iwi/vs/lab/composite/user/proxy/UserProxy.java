package de.hska.iwi.vs.lab.composite.user.proxy;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import de.hska.iwi.vs.lab.composite.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@EnableHystrixDashboard
@RestController
public class UserProxy {

    private final Map<Long, User> userCache = new LinkedHashMap<Long, User>();

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "getUserCache", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    @GetMapping(value="/",produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(Long userId) {
        User tmpuser =
                restTemplate.getForObject("http://localhost:9090/api/customers/" + userId, User.class);
        userCache.putIfAbsent(userId, tmpuser);
        return tmpuser;
    }

    public User getUserCache(Long userId) {
        return userCache.getOrDefault(userId, new User());
    }
}