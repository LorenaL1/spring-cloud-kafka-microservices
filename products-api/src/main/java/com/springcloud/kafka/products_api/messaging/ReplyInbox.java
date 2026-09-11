package com.springcloud.kafka.products_api.messaging;

import com.springcloud.kafka.products_api.models.Reply;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ReplyInbox {
    private final ConcurrentHashMap<String, CompletableFuture<Reply<?>>> pending = new ConcurrentHashMap<>();

    public CompletableFuture<Reply<?>> register(String correlationId) {
        CompletableFuture<Reply<?>> future = new CompletableFuture<>();
        pending.put(correlationId, future);
        return future;
    }

    public void complete(String correlationId, Reply<?> reply){
        if(correlationId == null){
            throw new RuntimeException("correlationId can't be null");
        }

        CompletableFuture<Reply<?>> future = pending.remove(correlationId);
        if(future != null){
            future.complete(reply);
        }
    }
}
