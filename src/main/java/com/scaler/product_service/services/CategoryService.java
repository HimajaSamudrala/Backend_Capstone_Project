package com.scaler.product_service.services;

import com.scaler.product_service.exceptions.CategoryNotExistException;
import com.scaler.product_service.models.Category;
import com.scaler.product_service.models.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public List<Category> getAllCategories();
    public List<Product> getInCategory(String category) throws CategoryNotExistException;

}
