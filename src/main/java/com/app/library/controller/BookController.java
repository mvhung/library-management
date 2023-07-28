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

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    BookServiceImpl bookServiceImpl;
    @Autowired
    MapValidationErrorService mapValidationErrorService;
    @RequestMapping("{id}")
    public ResponseEntity<?> getBookById(@PathVariable(name="id") int id ){

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
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookServiceImpl.getAllBooks();
        return new ResponseEntity<>(books,HttpStatus.OK);
    }
    @GetMapping("/pages")
    public ResponseEntity<?> getBooks(
            @PageableDefault(size = 5, sort ="bookTitle", direction = Sort.Direction.ASC)
            Pageable pageable){
        Page<Book> bookPage =  bookServiceImpl.findAll(pageable);
        return new ResponseEntity<>(bookPage, HttpStatus.OK);
    }
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Book>> getBooksByCategoryName(@PathVariable String categoryName) {
        List<Book> books = bookServiceImpl.getBooksByCategoryName(categoryName);
        return new ResponseEntity<>(books,HttpStatus.OK);
    }
    @GetMapping("/publisher/{publisherName}")
    public  ResponseEntity<List<Book>> getBooksByPublisherName(@PathVariable String publisherName) {
        List<Book> books = bookServiceImpl.getBooksByPublisherName(publisherName);
        return new ResponseEntity<>(books,HttpStatus.OK);
    }

    @GetMapping("/author/{authorName}")
    public ResponseEntity<List<Book>> getBookByAuthorName(@PathVariable String authorName) {
        List<Book> books = bookServiceImpl.getBookByAuthorName(authorName);
        return new ResponseEntity<>(books,HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchBooks(@RequestParam("keyword") String keyword) {
        return bookServiceImpl.searchBook(keyword);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBook(
            @Valid @PathVariable(name="id") int id,
            @RequestBody BookDto dto , BindingResult result) {

        ResponseEntity<?> responseEntity =  mapValidationErrorService.mapValidationFields(result);
        if(responseEntity != null) {
            return responseEntity;
        }
        return bookServiceImpl.updateBook(id,dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(name="id") int id) {
        return bookServiceImpl.deleteBook(id);
    }


}