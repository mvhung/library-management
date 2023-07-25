package com.app.library.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class MapValidationErrorService {
//    service này sẽ kiểm tra dư liệu
    public ResponseEntity<?> mapValidationFields(BindingResult result){
        if(result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error: result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
//             với HTTP status code là 400 Bad Request
//              ResponseEntity trả về errorMap làm response body
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
        return null;

    }
}
