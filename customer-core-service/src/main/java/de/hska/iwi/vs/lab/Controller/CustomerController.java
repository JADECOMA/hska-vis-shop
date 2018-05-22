package de.hska.iwi.vs.lab.Controller;

import de.hska.iwi.vs.lab.Entity.Customer;
import de.hska.iwi.vs.lab.Entity.LoginData;
import de.hska.iwi.vs.lab.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping()
    @ResponseBody
    public HttpStatus addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @GetMapping()
    @ResponseBody
    public Iterable<Customer> listCustomer() {
        return customerService.listCustomer();
    }

    @PostMapping("/login")
    @ResponseBody
    public HttpStatus login(@RequestBody LoginData loginData) {
        return customerService.login(loginData);
    }

    @GetMapping("/logout")
    public HttpStatus logout() {
        return HttpStatus.OK;
    }

    @RequestMapping("/test/{testIt}")
    public String ok(@PathVariable String testIt) {
        return testIt;
    }
}
