package com.scaler.product_service.controllers;

import com.scaler.product_service.exceptions.ProductDoesNotExistException;
import com.scaler.product_service.models.Category;
import com.scaler.product_service.models.Product;
import com.scaler.product_service.repositories.CategoryRepository;
import com.scaler.product_service.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductController(@Qualifier("selfProductService") ProductService productService,
                             CategoryRepository categoryRepository)
    {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts()
    {
        //return productService.getAllProducts();
        ResponseEntity<List<Product>> response = new
                ResponseEntity<>(productService.getAllProducts(), HttpStatus.NOT_FOUND);

        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id) throws ProductDoesNotExistException {
        return new ResponseEntity<>(productService.getSingleProduct(id), HttpStatus.OK);
    }

    @PostMapping()
    public Product addNewProduct(@RequestBody Product product)
    {
        return productService.addNewProduct(product);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product)
    {
        return productService.updateProduct(id, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductDoesNotExistException {
        return productService.replaceProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable("id") Long id)
    {
        return productService.deleteProduct(id);
    }
}
