package com.app.library.exception.category;

import lombok.Data;

@Data
public class CategoryExceptionResponse {
    private  String message ;
    public CategoryExceptionResponse(String message) {
        this.message = message;
    }
}
