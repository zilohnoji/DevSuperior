package com.devsuperior.dscommerce.builders.entities;

import com.devsuperior.dscommerce.builders.Builder;
import com.devsuperior.dscommerce.entities.Product;

public interface ProductSpecificationBuilder extends Builder<Product> {

    ProductSpecificationBuilder name(String name);

    ProductSpecificationBuilder description(String description);

    ProductSpecificationBuilder price(Double price);
}