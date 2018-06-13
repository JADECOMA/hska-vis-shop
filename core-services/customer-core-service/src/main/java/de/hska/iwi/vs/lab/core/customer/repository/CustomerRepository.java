package de.hska.iwi.vs.lab.core.customer.repository;

import de.hska.iwi.vs.lab.core.customer.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Optional<Customer> findByUsernameIgnoreCase(String username);
}