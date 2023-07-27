package com.app.library.service;

import com.app.library.dto.BookRequestDto;
import org.springframework.http.ResponseEntity;

import com.app.library.model.Book;

import java.util.List;

public interface IBookService {
    public ResponseEntity<?> getBook(int id);
    public List<Book> getAllBooks() ;

    public ResponseEntity<?> addBook(BookRequestDto dto);
    public ResponseEntity<?> updateBook(int id, BookRequestDto dto);
    ResponseEntity<?> deleteBook(int id);
    ResponseEntity<?> searchBook(String keyword);

}
