package com.vyatsu.task14.repositories;

import com.vyatsu.task14.entities.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1L, "Bread", 40));
        products.add(new Product(2L, "Milk", 90));
        products.add(new Product(3L, "Cheese", 200));
    }

    public List<Product> findAll() {
        return products;
    }

    public List<Product> findAll(Integer Min,Integer Max,String title) {
        List<Product> prod = products;
        if (Min != null) {
            prod = prod.stream().filter(p -> p.getPrice() >= Min).collect(Collectors.toList());
        }
        if (Max != null) {
            prod = prod.stream().filter(p -> p.getPrice() <= Max).collect(Collectors.toList());
        }
        if (title != "") {
            prod = prod.stream().filter(p -> p.getTitle().contains(title)).collect(Collectors.toList());
        }
        return prod;
    }


    public Product findByTitle(String title) {
        return products.stream().filter(p -> p.getTitle().equals(title)).findFirst().get();
    }

    public Product findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().get();
    }

    public void save(Product product) {
        products.add(product);
    }

    public void remove(Product product) {
        products.remove(product);
    }

    public void edit(Product product) {
        products.remove(this.findById(product.getId()));
        products.add(product);
    }
}
