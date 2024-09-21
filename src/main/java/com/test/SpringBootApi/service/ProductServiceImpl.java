package com.test.SpringBootApi.service;

import com.test.SpringBootApi.domain.Product;
import com.test.SpringBootApi.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        try {
            return productRepository
                    .save(
                    new Product(
                            product.getBookName(),
                            product.getPrice(),
                            product.getAuthor()
                    )
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Optional<Product> findById(Long id) {
        try {
            Optional<Product> productData = productRepository.findById(id);
            if (productData.isPresent()) {
                return productData;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product update(Long id, Product product) {

        try {
            Optional<Product> productData = productRepository.findById(id);
            if (productData.isPresent()) {
                Product _product = productData.get();
                _product.setBookName(product.getBookName());
                _product.setPrice(product.getPrice());
                _product.setAuthor(product.getAuthor());
                productRepository.save(_product);
                return _product;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
