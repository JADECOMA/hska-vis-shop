package de.hska.iwi.vs.lab.core.category.repository;

import de.hska.iwi.vs.lab.core.category.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
}