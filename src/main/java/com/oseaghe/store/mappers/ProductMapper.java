package com.oseaghe.store.mappers;

import com.oseaghe.store.dtos.ProductDto;
import com.oseaghe.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);

    @Mapping(target ="id" , ignore= true)
    void update(ProductDto productDto, @MappingTarget Product product);
}
