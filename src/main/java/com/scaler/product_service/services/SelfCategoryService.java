package com.scaler.product_service.services;

import com.scaler.product_service.exceptions.CategoryNotExistException;
import com.scaler.product_service.models.Category;
import com.scaler.product_service.models.Product;
import com.scaler.product_service.repositories.CategoryRepository;
import com.scaler.product_service.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service//("selfCategoryService")
public class SelfCategoryService implements CategoryService{
    CategoryRepository categoryRepository;
    ProductRepository productRepository;

    public SelfCategoryService(CategoryRepository categoryRepository, ProductRepository productRepository)
    {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getInCategory(String category) throws CategoryNotExistException {
        Optional<Category> categoryOptional = categoryRepository.findByName(category);
        if(categoryOptional.isEmpty())
        {
            throw new CategoryNotExistException(category+" doesn't exist");
        }

        //return categoryRepository.findByName(category);
        return productRepository.findByCategory(categoryOptional.get());
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAllCategories();
    }

}
