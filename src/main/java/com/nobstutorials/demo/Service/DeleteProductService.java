package com.nobstutorials.demo.Service;

import com.nobstutorials.demo.Exceptions.ProductNotFoundException;
import com.nobstutorials.demo.Model.Product;
import com.nobstutorials.demo.Product.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for deleting a product by ID.
 * Implements the {@code Command} interface for handling delete operations.
 */
@Service
public class DeleteProductService implements Command<Integer, Void> {

    private final ProductRepository productRepository;

    /**
     * Constructor to initialize the {@code DeleteProductService} with a {@code ProductRepository}.
     *
     * @param productRepository the repository used for accessing and deleting product data.
     */
    public DeleteProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Executes the delete operation for a product.
     *
     * @param id the ID of the product to delete.
     * @return a {@code ResponseEntity} with HTTP status {@code NO_CONTENT} if the product is successfully deleted.
     * @throws ProductNotFoundException if the product with the given ID is not found in the repository.
     */
    @Override
    public ResponseEntity<Void> execute(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        throw new ProductNotFoundException();
    }
}
