package com.devsuperior.dscommerce.tests;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Category;
import com.devsuperior.dscommerce.entities.Product;

public class ProductFactory {

    public static Product createProduct() {
        Category category = CategoryFactory.createCategory();
        Product product = new Product(1L, "Console PlayStation 5", "consectetur adipiscing elit, sed", 3999.0, "https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg");
        product.getCategories().add(category);
        return product;
    }

    public static Product createProductWithCategoryEmpty() {
        return new Product(1L, "Console PlayStation 5", "consectetur adipiscing elit, sed", 3999.0, "https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg");
    }

    public static Product createProductWithName(String name) {
        Product product = createProduct();
        product.setName(name);
        return product;
    }

    public static Product createProductWithPrice(double price) {
        Product product = createProduct();
        product.setPrice(price);
        return product;
    }

    public static ProductDTO createProductDtoWithName(String name) {
        return new ProductDTO(createProductWithName(name));
    }

    public static ProductDTO createProductDtoWithPrice(double price) {
        return new ProductDTO(createProductWithPrice(price));
    }

    public static ProductDTO createProductDto() {
        return new ProductDTO(createProduct());
    }
}