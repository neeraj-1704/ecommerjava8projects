//package com.commerce.ecommerce_api.service;
//
//
//import com.commerce.ecommerce_api.excpetions.InsufficientStockException;
//import com.commerce.ecommerce_api.excpetions.ProductNotFoundException;
//import com.commerce.ecommerce_api.model.Order;
//import com.commerce.ecommerce_api.model.Product;
//import com.commerce.ecommerce_api.repository.ProductRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class OrderService {
//
//    @Autowired
//    private ProductRepository productRepository;
////
//    @Transactional
//    public Order placeOrder(Long productId, int quantity){
//        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));
//        // 2. Check business rules (stock validation), else throw custom 400 exception
//        if (product.getStockQuantity() < quantity) {
//            throw new InsufficientStockException("Only " + product.getStockQuantity() + " items left in stock.");
//        }
//
//        // 3. Proceed with creating the order if validation passes
//        product.setStockQuantity(product.getStockQuantity() - quantity);
//        productRepository.save(product);
//
//        return createNewOrder(product, quantity);
//    }
//
//
//
//}
