package com.nobstutorials.demo.Service;

import com.nobstutorials.demo.Exceptions.ProductNotFoundException;
import com.nobstutorials.demo.Model.Product;
import com.nobstutorials.demo.Product.ProductDTO;
import com.nobstutorials.demo.Product.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for retrieving a product by its ID.
 * Implements the {@code Query} interface to handle retrieval operations.
 */
@Service
public class GetProductService implements Query<Integer, ProductDTO> {

    private final ProductRepository productRepository;

    /**
     * Constructor to initialize the {@code GetProductService} with a {@code ProductRepository}.
     *
     * @param productRepository the repository used for accessing product data.
     */
    public GetProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Executes the retrieval operation for a product by its ID.
     *
     * @param id the ID of the product to retrieve.
     * @return a {@code ResponseEntity} containing the {@code ProductDTO} if the product is found.
     * @throws ProductNotFoundException if the product with the given ID is not found in the repository.
     */
    @Override
    public ResponseEntity<ProductDTO> execute(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            return ResponseEntity.ok(new ProductDTO(productOptional.get()));
        }

        throw new ProductNotFoundException();
    }
}
