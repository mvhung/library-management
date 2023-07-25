package com.app.library.service;

import com.app.library.dto.BookDto;
import org.springframework.http.ResponseEntity;

import com.app.library.model.Book;

public interface IBookService {
    public ResponseEntity<?> getBook(int id);
    public ResponseEntity<?> getAllBooks() ;

    public ResponseEntity<?> addBook(BookDto dto);
    public ResponseEntity<?> updateBook(int id, BookDto dto);
    ResponseEntity<?> deleteBook(int id);
    ResponseEntity<?> searchBook(String keyword);

}
