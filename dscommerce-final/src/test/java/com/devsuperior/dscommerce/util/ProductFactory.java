package com.devsuperior.dscommerce.util;

import com.devsuperior.dscommerce.dto.CategoryDTO;
import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Category;
import com.devsuperior.dscommerce.entities.Product;

public abstract class ProductFactory {

    private final static Long IDENTIFIER = 2000L;
    private final static String NAME = "Asus Computer";
    private final static String DESCRIPTION = "Best pc of the market";
    private final static Double PRICE = 400D;
    private final static CategoryDTO CATEGORYDTO = new CategoryDTO(1L, "Livros");
    private final static Category CATEGORY = new Category(1L, "Livros");

    public static ProductDTO createProductDTO() {
        var dto = new ProductDTO(IDENTIFIER, NAME, DESCRIPTION, PRICE, null);
        dto.getCategories().add(CATEGORYDTO);

        return dto;
    }

    public static Product createProductEntity() {
        var entity = new Product(null, NAME, DESCRIPTION, PRICE, null);
        entity.getCategories().add(CATEGORY);

        return entity;
    }
}