package com.scaler.product_service.controllers;

import com.scaler.product_service.exceptions.CategoryNotExistException;
import com.scaler.product_service.models.Category;
import com.scaler.product_service.models.Product;
import com.scaler.product_service.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(@Qualifier("selfCategoryService") CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }
    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategories () {

        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/{title}")
    public ResponseEntity<List<Product>> getInCategory(@PathVariable String title) throws CategoryNotExistException {
        return new ResponseEntity<>(categoryService.getInCategory(title), HttpStatus.FOUND);
    }
}
