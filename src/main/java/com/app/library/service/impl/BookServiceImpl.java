package com.app.library.service.impl;
import com.app.library.dto.BookDto;
import com.app.library.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import com.app.library.model.Book;
import com.app.library.repository.BookRepository;

@Service
public class BookServiceImpl implements com.app.library.service.IBookService {
    @Autowired
    private BookRepository bookRepository;
    public Book save(Book book) {
        return bookRepository.save(book);
    }
    @Override
    public ResponseEntity<Book> getBook(int id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("can't find loan id:" + id));

        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllBooks(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<?> addBook(BookDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateBook(int id, BookDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteBook(int id) {
        return null;
    }

    @Override
    public ResponseEntity<?> searchBook(String keyword) {
        return null;
    }

}