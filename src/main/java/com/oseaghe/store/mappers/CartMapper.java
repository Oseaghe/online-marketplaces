package com.oseaghe.store.mappers;

import com.oseaghe.store.dtos.CartDto;
import com.oseaghe.store.dtos.CartItemDto;
import com.oseaghe.store.entities.Cart;
import com.oseaghe.store.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "totalPrice", expression = "java(cart.getTotalPrice())")
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto (CartItem cartItem);
}
