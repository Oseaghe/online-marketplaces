package com.oseaghe.store.controllers;

import com.oseaghe.store.dtos.ProductDto;
import com.oseaghe.store.entities.Product;
import com.oseaghe.store.mappers.ProductMapper;
import com.oseaghe.store.repositories.CategoryRepository;
import com.oseaghe.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public List<ProductDto> getAllProducts(
            @RequestParam(name="category", required = false) Byte categoryId
            ){
        List<Product> products;
        if (categoryId != null) {
            products = productRepository.findAllByCategoryId(categoryId);
        }
        else{
            products = productRepository.findAllWithCategory();
        }
        return products.stream().map(productMapper::toDto).toList();
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(
            UriComponentsBuilder uriBuilder,
            @RequestBody ProductDto productDto) {
    try {
        var category = categoryRepository.findById(productDto.getCategoryId())
                .orElse(null);
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }

        var product = productMapper.toEntity(productDto);
        product.setCategory(category);
        product = productRepository.save(product);

        var uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(productDto);


    } catch (Exception e) {
        e.printStackTrace();  // Log the error appropriately in production
        return ResponseEntity.internalServerError().build();
    }
}
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id
    , @RequestBody ProductDto productDto)
    {
        var category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if(category == null){
            return ResponseEntity.badRequest().build();
        }
        var product = productRepository.findById(id).orElse(null);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        productMapper.update(productDto, product);
        product.setCategory(category);
        productRepository.save(product);
        productDto.setId(product.getId());
        return ResponseEntity.ok(productDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }
}
