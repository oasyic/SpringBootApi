package com.test.SpringBootApi.controller;

import com.test.SpringBootApi.service.ProductServiceImpl;
import com.test.SpringBootApi.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    @GetMapping("/books/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable("id") long id) {

        try {
            return ResponseEntity.ok(productService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/books")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(productService.save(product));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable("id") long id,
            @RequestBody Product product
    ) {
        try {
            ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(productService.update(id, product));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
        try {
            productService.delete(id);
            ResponseEntity.noContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
