package de.hska.iwi.vs.lab.Controller;

import de.hska.iwi.vs.lab.Entity.Customer;
import de.hska.iwi.vs.lab.Entity.LoginData;
import de.hska.iwi.vs.lab.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        HttpStatus status = customerService.addCustomer(customer);
        return new ResponseEntity<>(status);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listCustomer() {
        return new ResponseEntity<>(customerService.listCustomer(), HttpStatus.OK);
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginData loginData) {
        return new ResponseEntity<>(customerService.login(loginData), HttpStatus.OK);
    }

    @GetMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> logout() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/test/{testIt}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String ok(@PathVariable String testIt) {
        return testIt;
    }
}
