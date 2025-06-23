package com.oseaghe.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDto {
    private String name;
    private String email;
    private Long id;
}
