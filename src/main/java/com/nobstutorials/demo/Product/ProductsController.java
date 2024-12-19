package com.nobstutorials.demo.Product;

import com.nobstutorials.demo.Model.Product;
import com.nobstutorials.demo.Model.UpdateProductCommand;
import com.nobstutorials.demo.Service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing products.
 * Provides endpoints for creating, updating, retrieving, and deleting products.
 */
@RestController
public class ProductsController {

    private final GetProductsService getProductsService;
    private final CreateProductService createProductService;
    private final UpdateProductService updateProductService;
    private final DeleteProductService deleteProductService;
    private final GetProductService getProductService;
    private final SearchProductService searchProductService;

    /**
     * Constructor to initialize the {@code ProductsController} with required services.
     *
     * @param getProductsService service to retrieve all products.
     * @param createProductService service to create a new product.
     * @param updateProductService service to update an existing product.
     * @param deleteProductService service to delete a product by ID.
     * @param getProductService service to retrieve a product by ID.
     */
    public ProductsController(GetProductsService getProductsService, CreateProductService createProductService, UpdateProductService updateProductService, DeleteProductService deleteProductService, GetProductService getProductService, SearchProductService searchProductService) {
        this.getProductsService = getProductsService;
        this.createProductService = createProductService;
        this.updateProductService = updateProductService;
        this.deleteProductService = deleteProductService;
        this.getProductService = getProductService;
        this.searchProductService = searchProductService;
    }

    /**
     * Endpoint to create a new product.
     *
     * @param product the product details to be created.
     * @return a {@code ResponseEntity} containing the created {@code ProductDTO} with HTTP status {@code CREATED}.
     */
    @PostMapping("/product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product) {
        return createProductService.execute(product);
    }

    /**
     * Endpoint to update an existing product by ID.
     *
     * @param id the ID of the product to update.
     * @param product the updated product details.
     * @return a {@code ResponseEntity} containing the updated {@code ProductDTO}.
     */
    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        return updateProductService.execute(new UpdateProductCommand(product, id));
    }

    /**
     * Endpoint to retrieve all products.
     *
     * @return a {@code ResponseEntity} containing a list of all {@code ProductDTOs}.
     */
    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return getProductsService.execute(null);
    }

    /**
     * Endpoint to retrieve a product by ID.
     *
     * @param id the ID of the product to retrieve.
     * @return a {@code ResponseEntity} containing the {@code ProductDTO}.
     */
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
        return getProductService.execute(id);
    }

    /**
     * Endpoint to delete a product by ID.
     *
     * @param id the ID of the product to delete.
     * @return a {@code ResponseEntity} with HTTP status {@code NO_CONTENT}.
     */
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        return deleteProductService.execute(id);
    }

    @GetMapping("product/search")
    public ResponseEntity<List<ProductDTO>> searchProductByName(@RequestParam String name) {
        return searchProductService.execute(name);
    }
}
