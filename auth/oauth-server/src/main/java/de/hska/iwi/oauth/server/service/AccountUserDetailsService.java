package de.hska.iwi.oauth.server.service;

import de.hska.iwi.oauth.server.entity.Customer;
import de.hska.iwi.oauth.server.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AccountUserDetailsService implements UserDetailsService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    private static Logger log = LoggerFactory.getLogger(AccountUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        log.info("#####################################################################################");
        log.info("#####################################################################################");
        log.info("#####################################################################################");
        log.info("#####################################################################################");

        log.info(username);

        log.info("#####################################################################################");
        log.info("#####################################################################################");
        log.info("#####################################################################################");
        log.info("#####################################################################################");

        Optional<Customer> user = accountRepository.findByUsernameIgnoreCase(username);
        System.out.println(user.get().toString());


        return accountRepository
                .findByUsernameIgnoreCase(username)
                .map(account -> new User(account.getUsername(), account.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER")))
                .orElseThrow(() -> new UsernameNotFoundException("Could not find " + username));
    }
}