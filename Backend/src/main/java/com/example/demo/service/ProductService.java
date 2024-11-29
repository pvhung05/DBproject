package com.example.demo.service;
import com.example.demo.model.Food;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            product.setQuantity(updatedProduct.getQuantity());
            product.setQuantity(updatedProduct.getQuantity());
            return productRepository.save(product);
        } else {
            return null;
        }
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    public List<Product> getProductsByIds(List<Long> ids) {
        return productRepository.findAllById(ids);
    }

    public void reduceStock(String productName, int quantity) {
        Product product = productRepository.findByName(productName)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getQuantity() < quantity) {
            throw new RuntimeException("Not enough stock for product: " + product.getName());
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
    }
    public void checkFoodExpiry(String productName, Date today) {
        Product product = productRepository.findByName(productName)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product instanceof Food) {
            Food foodProduct = (Food) product;
            // Check expiry date
            if (foodProduct.getExpiryDate().before(today)) {
                throw new RuntimeException("Product " + foodProduct.getName() + " has expired and cannot be sold.");
            }
        }
    }

}



