package com.scaler.product_service.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    private String name;
    //private List<Product> products;
}
