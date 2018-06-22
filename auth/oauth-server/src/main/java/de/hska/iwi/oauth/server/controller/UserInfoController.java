package de.hska.iwi.oauth.server.controller;

import de.hska.iwi.oauth.server.entity.Authorities;
import de.hska.iwi.oauth.server.entity.Users;
import de.hska.iwi.oauth.server.repository.AuthoritiesRepository;
import de.hska.iwi.oauth.server.repository.UsersRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Adelheid Knodel
 */
@RestController
public class UserInfoController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    AuthoritiesRepository authoritiesRepository;

    @RequestMapping(value = "/user")
    public Principal userInfo(@AuthenticationPrincipal Principal user) {
        return user;
    }

    @PutMapping(value = "/addUsers")
    public ResponseEntity<HttpStatus> addUsers(@RequestBody String usersString) {
        JSONObject json = new JSONObject(usersString);

        Users users = new Users();
        users.setUsername(json.getString("username"));
        users.setPassword(json.getString("password"));
        users.setEnabled(true);

        usersRepository.save(users);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/addAuthorities")
    public ResponseEntity<HttpStatus> addAuthorities(@RequestBody String authoritiesString) {
        JSONObject json = new JSONObject(authoritiesString);

        Authorities authorities = new Authorities();
        authorities.setUsername(json.getString("username"));
        authorities.setAuthority(json.getString("authority"));

        authoritiesRepository.save(authorities);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
