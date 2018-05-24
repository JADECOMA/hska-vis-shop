package de.hska.iwi.vs.lab.Repository;

import de.hska.iwi.vs.lab.Entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByUsernameIgnoreCase(String username);
}