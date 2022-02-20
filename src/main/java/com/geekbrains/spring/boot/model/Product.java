package com.geekbrains.spring.boot.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
        private Long id;
        private String title;
        private int cost;
}
