package com.nobstutorials.demo.Model;

import lombok.Getter;

@Getter
public class UpdateProductCommand {
    private Product product;
    private Integer id;

    public UpdateProductCommand(Product product, Integer id) {
        this.product = product;
        this.id = id;
    }
}
