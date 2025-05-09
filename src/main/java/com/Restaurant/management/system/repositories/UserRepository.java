package com.Restaurant.management.system.repositories;

import com.Restaurant.management.system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long > {

    Optional<User> findFirstByEmail(String email);
}
