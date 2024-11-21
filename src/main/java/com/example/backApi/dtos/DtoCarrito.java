package com.example.backApi.dtos;

import lombok.Data;

@Data
public class DtoCarrito {
    private String username;
    private String producto;
    private Long cantidad;
}
