package de.hska.iwi.vs.lab.Repository;

import de.hska.iwi.vs.lab.Entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}