package com.scaler.product_service.controllerAdvice;

import com.scaler.product_service.dtos.ExceptionDto;
import com.scaler.product_service.exceptions.ProductDoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExceptionHandlers {

    @ExceptionHandler(ProductDoesNotExistException.class)
    public ResponseEntity<ExceptionDto> handleProductDoesNotExistException(ProductDoesNotExistException exception)
    {
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage(exception.getMessage());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
