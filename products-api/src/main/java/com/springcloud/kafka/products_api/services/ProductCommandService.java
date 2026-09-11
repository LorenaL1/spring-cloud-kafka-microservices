package com.springcloud.kafka.products_api.services;

import com.springcloud.kafka.products_api.models.Reply;
import com.springcloud.kafka.products_api.models.dto.ProductDto;

import java.time.Duration;

public interface ProductCommandService {

    Reply<?> sendCreateAndAwait(ProductDto dto, Duration timeout);

    Reply<?> sendReadAndAwait(Long id, Duration timeout);

    Reply<?> sendReadAllAndAwait(Duration timeout);
    Reply<?> sendUpdateAndAwait(Long id, ProductDto dto, Duration timeout);
    Reply<?> sendDeleteAndAwait(Long id, Duration timeout);
}
