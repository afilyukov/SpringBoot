package com.geekbrains.spring.boot.repositories;

import com.geekbrains.spring.boot.exceptions.BusinessException;
import com.geekbrains.spring.boot.model.Product;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class ProductInMemoryRepository implements ProductRepository{
    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = new ArrayList<>(Arrays.asList(
                new Product(121L, "Orange", 70),
                new Product(16L, "Spoon", 80),
                new Product(567L, "Charger", 600)
        ));
    }

    @Override
    public Product addOrUpdateProduct(Product p) {
        if (p.getId() != null) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId().equals(p.getId())) {
                    products.set(i, p);
                    return p;
                }
            }
        }
        Long newId = products.stream().mapToLong(Product::getId).max().orElse(0L) + 1L;
        p.setId(newId);
        products.add(p);
        return p;
    }

    @Override
    public List<Product> getAll() {
        return Collections.unmodifiableList(products);
    }

    @Override
    public Optional<Product> getProductById(Long id) throws BusinessException {
        Optional<Product> product = null;
        try {
            return product = products.stream().filter(p -> p.getId().equals(id)).findFirst();
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e.getCause(), "SOMETHING_GO_WRONG");
        } finally {
            //try to to do or close something here. File or connection for example
            if (product.isEmpty()) {
                throw new BusinessException("Repository layer cannot found a product with id" + id.toString(), "PRODUCT_NOT_FOUND");
            }
        }
    }

    @Override
    public void deleteProductById(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }

}
