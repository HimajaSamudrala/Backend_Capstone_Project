package com.scaler.product_service.services;

import com.scaler.product_service.dtos.FakeStoreProductDTO;
import com.scaler.product_service.exceptions.CategoryNotExistException;
import com.scaler.product_service.exceptions.ProductDoesNotExistException;
import com.scaler.product_service.models.Category;
import com.scaler.product_service.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FakeStoreProductService implements ProductService, CategoryService{

    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreProductToProduct(FakeStoreProductDTO fakeStoreProductDTO)
    {
        Product product = new Product();
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setId(fakeStoreProductDTO.getId());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDTO.getCategory());
        product.setImageURL(fakeStoreProductDTO.getImage());

        return product;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductDoesNotExistException {
        FakeStoreProductDTO productDTO = restTemplate.getForObject("https://fakestoreapi.com/products/"+id,
                FakeStoreProductDTO.class);

        if(productDTO == null)
        {
            throw new ProductDoesNotExistException("Product with id" +id+ " doesn't exist");
        }

        return convertFakeStoreProductToProduct(productDTO);
    }

    @Override
    public List<Product> getAllProducts() {
        //List<> of java uses generic type. while compiling the code, java knows what type of data is present in
        //the list but at runtime, java doesn't know what type of data is present in the List<>.
        //So by using Arrays, we can convert the products list into list of products in our output.
        FakeStoreProductDTO[] response = restTemplate.getForObject("https://fakestoreapi.com/products/",
                FakeStoreProductDTO[].class);

        List<Product> answer = new ArrayList<>();

        for(FakeStoreProductDTO dto: response)
        {
            answer.add(convertFakeStoreProductToProduct(dto));
        }

        return answer;
    }

    @Override
    public Product addNewProduct(Product product) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setId(product.getId());
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setImage(product.getImageURL());
        fakeStoreProductDTO.setCategory(product.getCategory().getName());

        //FakeStoreProductDTO productDTO = restTemplate.postForObject("https://fakestoreapi.com/products/",
        // FakeStoreProductDTO[].class);
        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDTO,
                                                                        FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor = new
                HttpMessageConverterExtractor(FakeStoreProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreProductDTO response =  restTemplate.execute("https://fakestoreapi.com/products",
                HttpMethod.POST, requestCallback, responseExtractor);

        return convertFakeStoreProductToProduct(response);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setId(product.getId());
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setImage(product.getImageURL());
        fakeStoreProductDTO.setCategory(product.getCategory().getName());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDTO,
                FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor = new
                HttpMessageConverterExtractor<>(FakeStoreProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreProductDTO response = restTemplate.execute("https://fakestoreapi.com/products/"+id,
                HttpMethod.PATCH, requestCallback, responseExtractor);

        return convertFakeStoreProductToProduct(response);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setImage(product.getImageURL());
        fakeStoreProductDTO.setCategory(product.getCategory().getName());

        //PUT method doesn't return anything because its return type is void. Instead of using put or patch
        //methods, we can use execute or exchange methods which are low level to GET,PUT,POST methods.
        //Exchange is a bit high level to execute method, we are using exchange method.
        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDTO,
                FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor = new
                HttpMessageConverterExtractor<>(FakeStoreProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreProductDTO response = restTemplate.execute("https://fakestoreapi.com/products/"+id,
                HttpMethod.PUT, requestCallback, responseExtractor);

        return convertFakeStoreProductToProduct(response);
    }

    @Override
    public void deleteProduct(Long id) {

        restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.DELETE, (RequestCallback)null, (ResponseExtractor)null);
        //restTemplate.delete("https://fakestoreapi.com/products/"+id);
    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public List<Product> getInCategory(String category) throws CategoryNotExistException {
        return null;
    }
}
