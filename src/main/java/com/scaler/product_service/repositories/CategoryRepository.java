package com.scaler.product_service.repositories;

import com.scaler.product_service.models.Category;
import com.scaler.product_service.repositories.projections.CategoryWithName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Override
    Optional<Category> findById(Long id);
    @Query(value = "select name from Category", nativeQuery = true)
    List<CategoryWithName> findAllCategories();

    Optional<Category> findByName(String category);

    //Example:
    //@Query(value = "select c.id as id, c.name as name from Category c where c.id = :id" , nativeQuery = true)
    //List<CategoryWithIdAndName> findAllCategories(@Param("id") Long id);
}
