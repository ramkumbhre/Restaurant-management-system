package com.Restaurant.management.system.repositories;


import com.Restaurant.management.system.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    List<Category> findAllByNameContaining(String title);
}
