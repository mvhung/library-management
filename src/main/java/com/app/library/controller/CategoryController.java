package com.app.library.controller;

import com.app.library.dto.CategoryDto;
import com.app.library.model.Category;
import com.app.library.service.CategoryService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping
    public ResponseEntity<?> SaveOrUpdate(@RequestBody CategoryDto dto) {
        //create category
        Category entity = new Category();
        //copy to dto -> entity
        BeanUtils.copyProperties(dto, entity);
        //lay gia tri cua entity duoc luu => truy cap toi thong tin cua id da duoc luu
        entity = categoryService.save(entity);
        dto.setCategoryId(entity.getCategoryId());
        //return response to client
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
