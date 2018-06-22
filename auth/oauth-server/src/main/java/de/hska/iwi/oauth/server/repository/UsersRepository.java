package de.hska.iwi.oauth.server.repository;

import de.hska.iwi.oauth.server.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsernameIgnoreCase(String username);
}