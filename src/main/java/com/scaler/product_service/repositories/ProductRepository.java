package com.scaler.product_service.repositories;

import com.scaler.product_service.models.Category;
import com.scaler.product_service.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    Optional<Product> findById(Long id);

    Product save(Product product);

    Product findByTitle(String title);

    Optional<Product> findProductById(Long id);

    List<Product> findByCategory(Category category);
}
