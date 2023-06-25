package com.donatoordep.dscommerce.mapper;

import com.donatoordep.dscommerce.dto.ProductDTO;
import com.donatoordep.dscommerce.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toEntity(ProductDTO dto);

    ProductDTO toDto(Product entity);
}
