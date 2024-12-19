package com.nobstutorials.demo.Service;

import com.nobstutorials.demo.Model.Product;
import com.nobstutorials.demo.Product.ProductDTO;
import com.nobstutorials.demo.Product.ProductRepository;
import com.nobstutorials.demo.Validators.ProductValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


/**
 * Service class for creating a new product.
 * Implements the {@code Command} interface for handling product creation requests.
 */
@Service
public class CreateProductService implements Command<Product, ProductDTO> {

    private final ProductRepository productRepository;

    /**
     * Constructor to initialize the {@code CreateProductService} with a {@code ProductRepository}.
     *
     * @param productRepository the repository used for saving product data.
     */
    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Executes the operation to create a new product.
     *
     * @param product the {@code Product} entity containing the details of the product to be created.
     * @return a {@code ResponseEntity} containing the created {@code ProductDTO} with HTTP status {@code CREATED}.
     */
    @Override
    public ResponseEntity<ProductDTO> execute(Product product) {
        ProductValidator.execute(product);
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductDTO(savedProduct));
    }
}
