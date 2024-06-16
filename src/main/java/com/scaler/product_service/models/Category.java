package com.scaler.product_service.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    private String name;
    //@OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE})
    //private List<Product> products;
}
