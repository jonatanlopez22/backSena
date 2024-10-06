package com.example.backApi.dtos;

import lombok.Data;

@Data
public class DtoRegistroRespuesta {
    private Boolean isSuccess ;


    public DtoRegistroRespuesta(Boolean access) {
        this.isSuccess = access;
    }
}