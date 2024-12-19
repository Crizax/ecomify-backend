package com.nobstutorials.demo.Validators;

import com.nobstutorials.demo.Exceptions.ProductNotValidException;
import com.nobstutorials.demo.Model.Product;
import org.springframework.util.StringUtils;

public class ProductValidator {
    ProductValidator(){

    }
    public static void execute(Product product){
        if (StringUtils.isEmpty(product.getName())){
            throw new ProductNotValidException("Name is required");
        }
        if (product.getDescription().length()<20){
            throw new ProductNotValidException("Description must be 20 characters long");
        }
        if (product.getPrice() == null || product.getPrice()<0.00){
            throw new ProductNotValidException("Price must be greater than 0");
        }
    }
}
