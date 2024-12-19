package com.nobstutorials.demo.Service;

import com.nobstutorials.demo.Exceptions.ProductNotFoundException;
import com.nobstutorials.demo.Model.Product;
import com.nobstutorials.demo.Model.UpdateProductCommand;
import com.nobstutorials.demo.Product.ProductDTO;
import com.nobstutorials.demo.Product.ProductRepository;
import com.nobstutorials.demo.Validators.ProductValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for handling product updates.
 * Implements the {@code Command} interface for executing product update operations.
 */
@Service
public class UpdateProductService implements Command<UpdateProductCommand, ProductDTO> {

    private final ProductRepository productRepository;

    /**
     * Constructor to initialize the {@code UpdateProductService} with a {@code ProductRepository}.
     *
     * @param productRepository the repository used for accessing and modifying product data.
     */
    public UpdateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Executes the update operation for a product.
     *
     * @param command the {@code UpdateProductCommand} containing the ID of the product to update and the new product details.
     * @return a {@code ResponseEntity} containing the updated {@code ProductDTO} if the product is found.
     * @throws ProductNotFoundException if the product with the given ID is not found in the repository.
     */
    @Override
    public ResponseEntity<ProductDTO> execute(UpdateProductCommand command) {
        Optional<Product> productOptional = productRepository.findById(command.getId());

        if (productOptional.isPresent()) {
            Product product = command.getProduct();
            product.setId(command.getId());
            ProductValidator.execute(product);
            productRepository.save(product);
            return ResponseEntity.ok(new ProductDTO(product));
        }

        throw new ProductNotFoundException();
    }
}
