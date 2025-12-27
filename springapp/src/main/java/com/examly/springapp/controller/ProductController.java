package com.examly.springapp.controller;

import com.examly.springapp.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private List<Product> productList = new ArrayList<>();
    private long productIdCounter = 1;
  @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        product.setProductId(productIdCounter++);
        productList.add(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
    // GET /api/products (Day10)
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productList);
    }

    // GET /api/products/{id} (Day10)
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        for (Product product : productList) {
            if (product.getProductId().equals(id)) {
                return ResponseEntity.ok(product);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // PUT /api/products/{id} (Day10)
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product updatedProduct) {

        for (Product product : productList) {
            if (product.getProductId().equals(id)) {
                product.setProductName(updatedProduct.getProductName());
                product.setDescription(updatedProduct.getDescription());
                product.setCategory(updatedProduct.getCategory());
                return ResponseEntity.ok(product);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // ✅ GET /api/products/category/{categoryName} (Day12 - Order 72)
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Product>> getProductsByCategoryName(
            @PathVariable String categoryName) {

        List<Product> result = new ArrayList<>();

        for (Product product : productList) {
            if (product.getCategory() != null) {
                result.add(product);
            }
        }

        return ResponseEntity.ok(result);
    }

    


    // ✅ GET /api/products/name/{name} (Day12 - Order 75 & 76)
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getProductsByName(@PathVariable String name) {

        List<Product> result = new ArrayList<>();

        for (Product product : productList) {
            if (product.getProductName() != null &&
                product.getProductName().equalsIgnoreCase(name)) {
                result.add(product);
            }
        }

        if (result.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No products found with name: " + name);
        }

        return ResponseEntity.ok(result);
    }
}

