package com.scaler.product_service.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
     @Id
     private Long id;
     private Date isCreatedAt;
     private Date lastUpdatedAt;
     private boolean isDeleted;
}
