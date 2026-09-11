package com.springcloud.kafka.products_api.models;

public record Reply<T>(ReplyStatus status, String message, T body) {
}
