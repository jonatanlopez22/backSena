package com.example.backApi.dtos;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class DtoRegistro {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String email;
}