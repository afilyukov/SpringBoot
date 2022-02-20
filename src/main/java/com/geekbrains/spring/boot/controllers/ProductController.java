package com.geekbrains.spring.boot.controllers;

import com.geekbrains.spring.boot.Services.ProductService;
import com.geekbrains.spring.boot.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping // GET http://localhost:8189/app/products
    public String showAll(Model model,
                          @RequestParam(required = false, name = "min_cost") Integer minCost,
                          @RequestParam(required = false, name = "max_cost") Integer maxCost
    ) {
        model.addAttribute("products", productService.getAll(minCost, maxCost));
        return "products"; // [http://localhost:8189/app]/products
    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Long id, HttpServletResponse response) {
        productService.deleteProductBydId(id);
        return "redirect:/products"; // [http://localhost:8189/app]/products
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.addOrUpdateProduct(product);
        return "redirect:/products"; // [http://localhost:8189/app]/products
    }

}
