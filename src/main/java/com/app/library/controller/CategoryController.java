package com.app.library.controller;

import com.app.library.dto.CategoryDto;
import com.app.library.service.impl.CategoryServiceImpl;
import com.app.library.service.impl.MapValidationErrorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    CategoryServiceImpl categoryServiceImpl;
    @Autowired
    MapValidationErrorService mapValidationErrorService;
    @PostMapping
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDto dto,
                                            BindingResult result){
        ResponseEntity<?> responseEntity = mapValidationErrorService.mapValidationFields(result);
        if(responseEntity != null) {
            return responseEntity;
        }
        return categoryServiceImpl.createCategory(dto);
    }
    @GetMapping
    public ResponseEntity<?> getCategories() {
        return categoryServiceImpl.getAllCategories();
    }

    @GetMapping("/search")
    public ResponseEntity<?> getCategory(@RequestParam("keyword") String keyword) {
        return categoryServiceImpl.searchCategory(keyword);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable(name = "id") int id) {
        return categoryServiceImpl.getCategory(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCategory(
            @PathVariable("id") int id,
            @RequestBody CategoryDto dto) {
        return categoryServiceImpl.updateCategory(id,dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") int id) {
        return categoryServiceImpl.deleteCategory(id);
    }

}
