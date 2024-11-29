package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Thêm mới sản phẩm
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    // Cập nhật sản phẩm
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product productDetails) {
        Product updatedProduct = productService.updateProduct(id, productDetails);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();  // Trả về 404 nếu sản phẩm không tồn tại
        }
    }

    // Lấy tất cả sản phẩm
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Lấy sản phẩm theo id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        try {
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();  // Trả về 404 nếu không tìm thấy sản phẩm
        }
    }

    // Xóa sản phẩm
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();  // Trả về 204 nếu xóa thành công
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();  // Trả về 404 nếu không tìm thấy sản phẩm
        }
    }
}
