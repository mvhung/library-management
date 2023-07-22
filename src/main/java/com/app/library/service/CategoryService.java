package com.app.library.service;

import com.app.library.model.Category;
import com.app.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
//    save thong tin cua entity duoc truyen vao
    public Category save(Category entity) {
        return categoryRepository.save(entity);
    }

    @Autowired
    private CategoryRepository categoryRepository;
}
