package com.geekbrains.spring.boot.repositories;

import com.geekbrains.spring.boot.exceptions.BusinessException;
import com.geekbrains.spring.boot.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product addOrUpdateProduct(Product product);
    Optional<Product> getProductById(Long id) throws BusinessException;
    List<Product> getAll();
    void deleteProductById(Long id);
}
