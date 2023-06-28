package com.devsuperior.uri2621;

import com.devsuperior.uri2621.projections.ProductMinProjection;

public class ProductMinDTO {

    private String name;

    public ProductMinDTO() {
    }

    public ProductMinDTO(ProductMinProjection obj) {
        name = obj.getName();
    }

    public ProductMinDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
