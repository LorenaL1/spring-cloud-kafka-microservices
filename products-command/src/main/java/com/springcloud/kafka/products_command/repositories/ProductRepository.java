package com.springcloud.kafka.products_command.repositories;

import com.springcloud.kafka.products_command.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
