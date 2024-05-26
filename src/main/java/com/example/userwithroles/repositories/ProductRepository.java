package com.example.userwithroles.repositories;

import com.example.userwithroles.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
