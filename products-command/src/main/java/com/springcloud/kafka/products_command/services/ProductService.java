package com.springcloud.kafka.products_command.services;

import com.springcloud.kafka.products_command.models.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();
    ProductDto findById(Long id);
    ProductDto create(ProductDto dto);
    ProductDto update(Long id, ProductDto dto);
    boolean delete(Long id);



}
