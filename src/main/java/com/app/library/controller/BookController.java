package com.app.library.controller;

import com.app.library.dto.BookDto;
import com.app.library.dto.CategoryDto;
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



}