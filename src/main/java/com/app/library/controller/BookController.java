package com.app.library.controller;

import com.app.library.dto.BookDto;
import com.app.library.model.Category;
import com.app.library.payload.PagedResponse;
import com.app.library.service.impl.MapValidationErrorService;
import com.app.library.utils.AppConstants;
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
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<?> addBook(@Valid @ModelAttribute BookDto dto,
                                     @RequestParam(value = "bookImageLink", required = false) MultipartFile bookImageLink,
                                     BindingResult result) {
        ResponseEntity<?> responseEntity = mapValidationErrorService.mapValidationFields(result);
        if (responseEntity != null) {
            return responseEntity;
        }
        return bookServiceImpl.addBook(dto, bookImageLink);
    }

    @GetMapping
    public PagedResponse<Book> getAllBooks(
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
        return bookServiceImpl.getAllBooks(page, size);
    }


    @GetMapping("/publisher/{publisherName}")
    public  PagedResponse<Book> getBooksByPublisherName(
        @PathVariable String publisherName,
        @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
        @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
       return bookServiceImpl.getBooksByPublisherName(publisherName,page,size);
    }

//    @GetMapping("/author/{authorName}")
//    public ResponseEntity<List<Book>> getBookByAuthorName(@PathVariable String authorName) {
//        List<Book> books = bookServiceImpl.getBookByAuthorName(authorName);
//        return new ResponseEntity<>(books,HttpStatus.OK);
//    }
//    @GetMapping("/search")
//    public ResponseEntity<?> searchBooks(@RequestParam("keyword") String keyword) {
//        return bookServiceImpl.searchBook(keyword);
//    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(
            @Valid @PathVariable(name="id") int id,
            @Valid @ModelAttribute BookDto dto ,
            @RequestParam(value = "bookImageLink", required = false) MultipartFile bookImageLink,
            BindingResult result) {

        ResponseEntity<?> responseEntity =  mapValidationErrorService.mapValidationFields(result);
        if(responseEntity != null) {
            return responseEntity;
        }
        return bookServiceImpl.updateBook(id,dto,bookImageLink);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(name="id") int id) {
        return bookServiceImpl.deleteBook(id);
    }


}