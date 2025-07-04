package com.Restaurant.management.system.repositories;

import com.Restaurant.management.system.entities.User;
import com.Restaurant.management.system.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long > {

    Optional<User> findFirstByEmail(String email);

      List<User> findByUserRole(UserRole userRole);


//    static User findByUserRole(UserRole userRole) {
//        return null;
//    }
}
