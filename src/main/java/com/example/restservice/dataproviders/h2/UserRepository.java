package com.example.restservice.dataproviders.h2;

import com.example.restservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * UserRepository interface for DB
 *
 * @author Skyhunter
 * @date 22.03.2021
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
