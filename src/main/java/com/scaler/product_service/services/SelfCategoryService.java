package com.scaler.product_service.services;

import com.scaler.product_service.exceptions.CategoryNotExistException;
import com.scaler.product_service.models.Category;
import com.scaler.product_service.models.Product;
import com.scaler.product_service.repositories.CategoryRepository;
import com.scaler.product_service.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelfCategoryService implements CategoryService{

    @Override
    public List<Product> getInCategory(String category) throws CategoryNotExistException {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }

}
