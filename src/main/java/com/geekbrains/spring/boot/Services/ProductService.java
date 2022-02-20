package com.geekbrains.spring.boot.Services;

import com.geekbrains.spring.boot.exceptions.BusinessException;
import com.geekbrains.spring.boot.model.Product;
import com.geekbrains.spring.boot.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

    @Service
    @RequiredArgsConstructor
    public class ProductService {
        private final ProductRepository pr;

        public Optional<Product> getProductById(Long id) {
            try {
                 return pr.getProductById(id); }
            catch (BusinessException e) {
                processErrorCodes(e);
                return null;
            }
        }

        public List<Product> getAll() {
            return pr.getAll();
        }

        public List<Product> getAll(Integer minCost, Integer maxCost) {
            List<Product> out = getAll();
            if (minCost != null) {
                out = out.stream().filter(p -> p.getCost() >= minCost).collect(Collectors.toList());
            }
            if (maxCost != null) {
                out = out.stream().filter(p -> p.getCost() <= maxCost).collect(Collectors.toList());
            }
            return out;
        }

        public Product addOrUpdateProduct(Product p) {
            return pr.addOrUpdateProduct(p);
        }

        public void deleteProductBydId(Long id) {
            pr.deleteProductById(id);
        }

        private void processErrorCodes(BusinessException e) {
            // здесь мы ищем указанный при выбросе исключения код ошибки и сообщаем пользователю что произошло
            switch(e.getErrorCode()){
                case "PRODUCT_NOT_FOUND_EXCEPTION":
                    System.out.println("Продукт не найден");
                    //do something
                case "FILE_CLOSE_EXCEPTION":
                    System.out.println("Ошибка при закрытии файла");
                   //do something
                case "CONNECTION_INIT_EXCEPTION":
                    System.out.println("Ошибка чтения бд");
                    //do something
                default:
                    System.out.println("Произошла неизвестная ошибка " + e.getMessage());
                    e.printStackTrace();
            }
        }
    }
