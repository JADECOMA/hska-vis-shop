package de.hska.iwi.vs.lab.core.customer.repository;

import de.hska.iwi.vs.lab.core.customer.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByUsernameIgnoreCase(String username);
}