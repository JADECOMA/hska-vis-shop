package de.hska.iwi.oauth.server.repository;

import de.hska.iwi.oauth.server.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by daz on 29/06/2017.
 */
public interface AccountRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByUsernameIgnoreCase(String username);
}