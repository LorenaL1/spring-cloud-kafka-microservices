package com.springcloud.kafka.products_command.models.dto;

public record ProductDto(
        Long id,
        String name,
        Double price
) {
}
