package com.scaler.product_service.services;

import com.scaler.product_service.exceptions.ProductDoesNotExistException;
import com.scaler.product_service.models.Category;
import com.scaler.product_service.models.Product;
import com.scaler.product_service.repositories.CategoryRepository;
import com.scaler.product_service.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service("selfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    private SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository)
    {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long id) throws ProductDoesNotExistException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty())
        {
            throw new ProductDoesNotExistException("Product with id"+id+"does not exist.");
        }
        Product product = productOptional.get();
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product addNewProduct(Product product) {

        //if(productRepository.findByTitle(product.getTitle()) == null) {
            Optional<Category> optionalCategory = categoryRepository.findByName(product.getCategory().getName());
            if (optionalCategory.isEmpty()) {
                product.setCategory(categoryRepository.save(product.getCategory()));
            } else {
                product.setCategory(optionalCategory.get());
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
