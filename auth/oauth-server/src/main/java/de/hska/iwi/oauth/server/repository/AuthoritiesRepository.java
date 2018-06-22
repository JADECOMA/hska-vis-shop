package de.hska.iwi.oauth.server.repository;

import de.hska.iwi.oauth.server.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {
}