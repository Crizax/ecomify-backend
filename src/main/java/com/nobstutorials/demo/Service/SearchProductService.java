package com.nobstutorials.demo.Service;

import com.nobstutorials.demo.Product.ProductDTO;
import com.nobstutorials.demo.Product.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchProductService implements Query<String, List<ProductDTO>>{

    private final ProductRepository productRepository;

    public SearchProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(String name) {
        return ResponseEntity.ok(productRepository.findByNameContaining(name)
                .stream()
                .map(ProductDTO::new)
                .toList());
    }
}
