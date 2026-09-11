package com.springcloud.kafka.products_api.controllers;

import com.springcloud.kafka.products_api.models.Reply;
import com.springcloud.kafka.products_api.models.ReplyStatus;
import com.springcloud.kafka.products_api.models.dto.ProductDto;
import com.springcloud.kafka.products_api.services.ProductCommandService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductCommandService commandService;

    public ProductController(ProductCommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ProductDto dto){
        return getResponseEntity(commandService.sendCreateAndAwait(dto, Duration.ofSeconds(5)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return getResponseEntity(commandService.sendReadAndAwait(id, Duration.ofSeconds(5)));
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return getResponseEntity(commandService.sendReadAllAndAwait(Duration.ofSeconds(5)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ProductDto dto){
        return getResponseEntity(commandService.sendUpdateAndAwait(id, dto, Duration.ofSeconds(5)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return getResponseEntity(commandService.sendDeleteAndAwait(id, Duration.ofSeconds(5)));
    }

    private ResponseEntity<?> getResponseEntity(Reply<?> reply) {
        if(reply.status().isSuccess()){
            return ResponseEntity.ok(reply.body());
        }
        return ResponseEntity.badRequest().body(Map.of("error", reply.message()));
    }
}
