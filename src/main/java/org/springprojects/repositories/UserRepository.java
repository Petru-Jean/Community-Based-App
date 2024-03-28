package org.springprojects.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springprojects.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //Optional<User> findByUsername(String username);
    User findByUsername(String username);

}