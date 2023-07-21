package com.app.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.library.model.Book;
import com.app.library.service.impl.IBookService;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    IBookService bookService;
    @RequestMapping("{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id){

        return bookService.getBook(id);
    }

}