package com.devsuperior.dscommerce.mappers.entities;

import com.devsuperior.dscommerce.dto.CategoryDTO;
import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Category;
import com.devsuperior.dscommerce.entities.Product;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

public final class ProductMapper {

    private ProductMapper() {
    }

    public static Product fromDto(ProductDTO dto) {
        return Product.ProductBuilder.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .imgUrl(dto.getImgUrl())
                .categories(new HashSet<>(converterCollections(dto.getCategories())))
                .build();
    }

    public static Product fromDto(Long id, ProductDTO dto) {
        return Product.ProductBuilder.builder()
                .id(id)
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .imgUrl(dto.getImgUrl())
                .categories(new HashSet<>(converterCollections(dto.getCategories())))
                .build();
    }

    private static Collection<Category> converterCollections(Collection<CategoryDTO> categoryDto) {
        return categoryDto.stream().map(categoryDTO -> new Category(categoryDTO.getId(), categoryDTO.getName())).collect(Collectors.toSet());
    }

}
