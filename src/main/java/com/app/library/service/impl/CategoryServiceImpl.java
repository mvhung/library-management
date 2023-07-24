package com.app.library.service.impl;

import com.app.library.dto.CategoryDto;
import com.app.library.exception.category.CategoryException;
import com.app.library.model.Category;
import com.app.library.repository.CategoryRepository;
import com.app.library.service.ICategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {
//    save thong tin cua entity duoc truyen vao
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<?> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getCategory(int id) {
        try {
            Category category = categoryRepository.findById(id).orElseThrow();
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (Exception e) {
            throw new CategoryException("No get detail category" );
        }
    }
    @Override
    public ResponseEntity<?> createCategory(CategoryDto dto) {
        Category category = new Category();

        //copy data từ CategoryDto sang CategoryEntity
        BeanUtils.copyProperties(dto, category);
        category = save(category);

        //cập nhật lại id của CategoryDto và trả lại CategoryDto cho client
        dto.setCategoryId(category.getCategoryId());
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateCategory(int id, CategoryDto dto) {
        Category category = new Category();

        //copy data từ CategoryDto sang CategoryEntity
        BeanUtils.copyProperties(dto, category);

        category = update(id, category);

        //cập nhật lại id của CategoryDto và trả lại CategoryDto cho client
        dto.setCategoryId(category.getCategoryId());
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    public Category update(int id, Category category) {

        Optional<Category> existed = categoryRepository.findById(id);
        if(existed.isEmpty()) {
            throw new CategoryException("Category id " + id + " does't exist");
        }

        try {
            Category existedCategory = existed.get();
            existedCategory.setCategoryName(category.getCategoryName());
            existedCategory.setCategoryDescription(category.getCategoryDescription());

            return categoryRepository.save(existedCategory);
        } catch (Exception e) {
            throw new CategoryException("Category is updated failed ");
        }
    }
    @Override
    public ResponseEntity<?> deleteCategory(int id) {
        try {
            Category existed = findById(id);
            categoryRepository.delete(existed);
            return new ResponseEntity<>("Category with Id " +id+" was deleted", HttpStatus.OK);
        } catch (Exception e) {
            throw new CategoryException("deleted category failed ");
        }

    }
    @Override
    public ResponseEntity<?> searchCategory(String keyword) {
        try {
            ResponseEntity<List<Category>> categoriesResponse = (ResponseEntity<List<Category>>) getAllCategories();
            List<Category> categories = categoriesResponse.getBody();
            //            List<Category> categories = categoryRepository.findAll();
            List<Category> results =  categories.stream()
                    .filter(category -> category.getCategoryName().toLowerCase().contains(keyword.toLowerCase()))
                    .collect(Collectors.toList());
            if (results.size() == 0) {
                return new ResponseEntity<>("No result for " + keyword ,HttpStatus.OK);
            }
            return new ResponseEntity<List<Category>>(results, HttpStatus.OK);
        } catch (Exception e) {
            throw new CategoryException("Can't find category ");
        }
    }


    public Category findById(int id) {

        Optional<Category> found = categoryRepository.findById(id);
        if(found.isEmpty()) {
            throw new CategoryException("Category with id " + id + " doesn't exist");
        }

        return found.get();
    }
}
