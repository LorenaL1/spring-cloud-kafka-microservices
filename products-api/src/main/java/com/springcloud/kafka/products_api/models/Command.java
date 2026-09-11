package com.springcloud.kafka.products_api.models;

public record Command<T>(
        CommandType type,
        Long id,
        T body
) {
}
