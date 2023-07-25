package com.app.library.service;

import com.app.library.dto.CategoryDto;
import com.app.library.model.Category;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {
    ResponseEntity<?> getAllCategories();
    ResponseEntity<?> getCategory(int id);
    ResponseEntity<?> createCategory(CategoryDto dto) ;
    ResponseEntity<?> updateCategory(int id, CategoryDto dto);
    ResponseEntity<?> deleteCategory(int id);
    ResponseEntity<?> searchCategory(String keyword);

}
