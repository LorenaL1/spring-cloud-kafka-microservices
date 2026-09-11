package com.springcloud.kafka.products_command.models;

public record Command<T>(
        CommandType type,
        Long id,
        T body
) {
}
