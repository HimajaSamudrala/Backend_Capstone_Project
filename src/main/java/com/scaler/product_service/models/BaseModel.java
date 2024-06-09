package com.scaler.product_service.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;
     private Date isCreatedAt;
     private Date lastUpdatedAt;
     private boolean isDeleted;
}
