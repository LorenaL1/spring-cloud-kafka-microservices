package com.springcloud.kafka.products_api.handlers;

import com.springcloud.kafka.products_api.messaging.ReplyInbox;
import com.springcloud.kafka.products_api.models.Reply;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
public class RepliesConsumer {

    private final ReplyInbox replyInbox;


    public RepliesConsumer(ReplyInbox replyInbox) {
        this.replyInbox = replyInbox;
    }

    @Bean
    public Consumer<Message<Reply<?>>> handleReplies(){
        return message -> {
            String correlationId = message.getHeaders().get("correlationId", String.class);
            replyInbox.complete(correlationId, message.getPayload());
        };
    }
}
