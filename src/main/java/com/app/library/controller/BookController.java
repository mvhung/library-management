package com.app.library.controller;

import com.app.library.dto.BookDto;
import com.app.library.service.impl.MapValidationErrorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.app.library.model.Book;
import com.app.library.service.impl.BookServiceImpl;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    BookServiceImpl bookServiceImpl;
    @Autowired
    MapValidationErrorService mapValidationErrorService;
    @RequestMapping("{id}")
    public ResponseEntity<?> getBookById(@PathVariable("id") int id){
        return bookServiceImpl.getBook(id);
    }
    @PostMapping
    public ResponseEntity<?> addBook(@Valid @RequestBody BookDto dto,
                                        BindingResult result) {
        ResponseEntity<?> responseEntity =  mapValidationErrorService.mapValidationFields(result);
        if(responseEntity != null) {
            return responseEntity;
        }
        return bookServiceImpl.addBook(dto);
    }
    @GetMapping("/pages")
    public ResponseEntity<?> getBooks(
            @PageableDefault(size = 5, sort ="bookTitle", direction = Sort.Direction.ASC)
            Pageable pageable){
        Page<Book> bookPage =  bookServiceImpl.findAll(pageable);
        return new ResponseEntity<>(bookPage, HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchBook(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "publishYear", required = false) Integer publishYear,
            @RequestParam(name = "category", required = false) String category) {
        return bookServiceImpl.searchBook(title,publishYear,category);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCategory(
            @Valid @PathVariable("id") int id,
            @RequestBody BookDto dto , BindingResult result) {

        ResponseEntity<?> responseEntity =  mapValidationErrorService.mapValidationFields(result);
        if(responseEntity != null) {
            return responseEntity;
        }
        return bookServiceImpl.updateBook(id,dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") int id) {
        return bookServiceImpl.deleteBook(id);
    }


}