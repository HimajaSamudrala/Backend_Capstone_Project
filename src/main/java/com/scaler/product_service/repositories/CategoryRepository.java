package com.scaler.product_service.repositories;

import com.scaler.product_service.models.Category;
import com.scaler.product_service.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Override
    Optional<Category> findById(Long id);
    @Query(value = "select c.* from Category c", nativeQuery = true)
    List<Category> findAllCategories();

    Optional<Category> findByName(String category);
    //List<Product> findByTitle(String name);
}
