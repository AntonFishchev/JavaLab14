package com.vyatsu.task14.services;

import com.vyatsu.task14.entities.Product;
import com.vyatsu.task14.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAllProducts(Integer Min,Integer Max,String title) {
        return productRepository.findAll(Min, Max, title);
    }

    public void add(Product product) {
        productRepository.save(product);
    }

    public void deleteById(Long id) {
        Product product = productRepository.findById(id);
        productRepository.remove(product);
    }

    public void editProduct(Product product) {
        if (productRepository.findById(product.getId()) != null) {
            productRepository.edit(product);
        }
    }
}
