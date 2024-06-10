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
        return productRepository.findAll();
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
        Optional<Product> productOptional = productRepository.findById(id);
        Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());
        if(productOptional.isEmpty()) throw new RuntimeException();
        Product savedProduct = productOptional.get();

        if(product.getTitle() != null)
        {
            savedProduct.setTitle(product.getTitle());
        }
        if(product.getPrice() != null)
        {
            savedProduct.setPrice(product.getPrice());
        }
        if(product.getDescription() != null)
        {
            savedProduct.setDescription(product.getDescription());
        }
        if(product.getImageURL() != null)
        {
            savedProduct.setImageURL(product.getImageURL());
        }
        if(categoryOptional.isEmpty())
        {
            Product oldProduct = productRepository.findByTitle(product.getTitle());
            savedProduct.setCategory(categoryRepository.save(product.getCategory()));
        }
        else
        {
            Product oldProduct = productRepository.findByTitle(product.getTitle());
            savedProduct.setCategory(categoryOptional.get());
        }

        return productRepository.save(savedProduct);
    }

    @Override
    public Product replaceProduct(Long id, Product product) throws ProductDoesNotExistException{
        Optional<Product> productOptional = productRepository.findById(id);
        Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());
        if(productOptional.isEmpty())
        {
            throw new ProductDoesNotExistException("Product with id"+id+"doesn't exist");
        }
        Product savedProduct = productOptional.get();

        savedProduct.setTitle(product.getTitle());
        savedProduct.setPrice(product.getPrice());
        savedProduct.setDescription(product.getDescription());
        if(categoryOptional.isEmpty())
        {
            savedProduct.setCategory(categoryRepository.save(product.getCategory()));
        }
        else
        {
            savedProduct.setCategory(categoryOptional.get());
        }
        savedProduct.setImageURL(product.getImageURL());

        return productRepository.save(savedProduct);
    }

    @Override
    public Product deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findProductById(id);
        productRepository.deleteById(id);
        return optionalProduct.get();
    }
}
