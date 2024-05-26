package com.example.userwithroles.service;

import com.example.userwithroles.entities.Product;
import com.example.userwithroles.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Object findById(long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            return product;
        } else {

            return "ERROR: Product with ID: " + id + " does not exist";
        }
    }

    public Product createProduct(Product newProductInfo) {

        Product product = new Product(newProductInfo.getProductName());

        productRepository.save(product);

        return product;
    }
}
