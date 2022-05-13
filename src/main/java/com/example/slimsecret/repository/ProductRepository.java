package com.example.slimsecret.repository;

import com.example.slimsecret.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
