package com.devsuperior.dscommerce.tests;

import com.devsuperior.dscommerce.entities.Product;

public final class ProductFactory {

    private static final Long IDENTIFIER = 1L;
    private static final String NAME = "Console PlayStation 5";
    private static final String DESCRIPTION = "consectetur adipiscing elit, sed";
    private static final Double PRICE = 3999.0D;
    private static final String IMG_URL = "https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg";

    private ProductFactory() {
    }

    public static Product createProduct() {
        return Product.ProductBuilder.builder()
                .id(IDENTIFIER)
                .name(NAME)
                .description(DESCRIPTION)
                .price(PRICE)
                .category(CategoryFactory.createCategory())
                .imgUrl(IMG_URL)
                .build();
    }

    public static Product createProductWithName(String name) {
        return Product.ProductBuilder.builder()
                .id(IDENTIFIER)
                .name(name)
                .description(DESCRIPTION)
                .price(PRICE)
                .category(CategoryFactory.createCategory())
                .imgUrl(IMG_URL)
                .build();
    }

    public static Product createProductWithIdentifier(Long id) {
        return Product.ProductBuilder.builder()
                .id(id)
                .name(NAME)
                .description(DESCRIPTION)
                .price(PRICE)
                .category(CategoryFactory.createCategory())
                .imgUrl(IMG_URL)
                .build();
    }

    public static Product createProductWithDescription(String description) {
        return Product.ProductBuilder.builder()
                .id(IDENTIFIER)
                .name(NAME)
                .description(description)
                .price(PRICE)
                .category(CategoryFactory.createCategory())
                .imgUrl(IMG_URL)
                .build();
    }

    public static Product createProductWithPrice(Double price) {
        return Product.ProductBuilder.builder()
                .id(IDENTIFIER)
                .name(NAME)
                .description(DESCRIPTION)
                .price(price)
                .category(CategoryFactory.createCategory())
                .imgUrl(IMG_URL)
                .build();
    }
}