package com.commerce.ecommerce_api.service;

import com.commerce.ecommerce_api.Mapper.ProductMapper;
import com.commerce.ecommerce_api.dto.request.ProductRequest;
import com.commerce.ecommerce_api.dto.response.ProductResponse;
import com.commerce.ecommerce_api.model.Product;
import com.commerce.ecommerce_api.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

        public ProductResponse createProduct(ProductRequest productRequest) {

            Product product = productMapper.mapToEntityProduct(productRequest);
            Product savedProduct = productRepository.save(product);

            return productMapper.mapProductToResponse(savedProduct);
        }


    // GET PRODUCT BY ID
    public ProductResponse getProductById(Long id) {

        Product product =
                productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return productMapper.mapProductToResponse(product);
    }


    public  List<ProductResponse> getProduct() {

        List<Product> products =
                productRepository.findAll();

        if(products.isEmpty()) {
            throw new RuntimeException("No products found");
        }

        return products.stream()
                .map(productMapper::mapProductToResponse)
                .collect(Collectors.toList());
    }

    // UPDATE PRODUCT
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        existingProduct.setName(productRequest.getName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setStockQuntity(productRequest.getStockQuntity());
        existingProduct.setCategory(productRequest.getCategory());
        existingProduct.setImage(productRequest.getImage());
        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.mapProductToResponse(updatedProduct);
    }

    // DELETE PRODUCT
    public String deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
        return "Product deleted successfully";
    }

}
