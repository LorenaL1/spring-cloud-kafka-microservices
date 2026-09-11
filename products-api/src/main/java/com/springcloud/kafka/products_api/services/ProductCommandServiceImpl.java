package com.springcloud.kafka.products_api.services;

import com.springcloud.kafka.products_api.messaging.ReplyInbox;
import com.springcloud.kafka.products_api.models.Command;
import com.springcloud.kafka.products_api.models.CommandType;
import com.springcloud.kafka.products_api.models.Reply;
import com.springcloud.kafka.products_api.models.dto.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class ProductCommandServiceImpl implements ProductCommandService{
    private final StreamBridge bridge;
    private final ReplyInbox replyInbox;
    private static final Logger logger = LoggerFactory.getLogger(ProductCommandServiceImpl.class);

    public ProductCommandServiceImpl(StreamBridge bridge, ReplyInbox replyInbox) {
        this.bridge = bridge;
        this.replyInbox = replyInbox;
    }

    @Override
    public Reply<?> sendCreateAndAwait(ProductDto dto, Duration timeout) {
        return sendAndAwait(new Command<>(CommandType.CREATE, null, dto), timeout);
    }

    @Override
    public Reply<?> sendReadAndAwait(Long id, Duration timeout) {
        return sendAndAwait(new Command<>(CommandType.READ, id, null), timeout);
    }

    @Override
    public Reply<?> sendReadAllAndAwait(Duration timeout) {
        return sendAndAwait(new Command<>(CommandType.READ_ALL, null, null), timeout);
    }

    @Override
    public Reply<?> sendUpdateAndAwait(Long id, ProductDto dto, Duration timeout) {
        return sendAndAwait(new Command<>(CommandType.UPDATE, id, dto), timeout);
    }

    @Override
    public Reply<?> sendDeleteAndAwait(Long id, Duration timeout) {
        return sendAndAwait(new Command<>(CommandType.DELETE, id, null), timeout);
    }

    private Reply<?> sendAndAwait(Command<?> cmd, Duration timeout) {
        String correlationId = UUID.randomUUID().toString();
        logger.info("Api Products Client Creating product with CorrelationId={}", correlationId);
        var future = replyInbox.register(correlationId);

        var msg = MessageBuilder.withPayload(cmd).setHeader("correlationId", correlationId).build();

        boolean send = this.bridge.send("commands-out-0", msg);

        if(!send){
            throw new IllegalStateException("No se pudo enviar el comando a kafka");
        }
        try {
            return future.get(timeout.toMillis(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException("Timeout esperando respuesta de products-commands desde kafka" ,e);
        }
    }

}
