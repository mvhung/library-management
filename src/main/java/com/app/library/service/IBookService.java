package com.app.library.service;

import com.app.library.dto.BookDto;
import org.springframework.http.ResponseEntity;

import com.app.library.model.Book;

import java.util.List;

public interface IBookService {
    public ResponseEntity<?> getBook(int id);
    public List<Book> getAllBooks() ;
    public List<Book> getBooksByCategoryName(String categoryName);
    public List<Book> getBooksByPublisherName(String publisherName);
    public List<Book> getBookByAuthorName(String authorFullName);
    public ResponseEntity<?> addBook(BookDto dto);
    public ResponseEntity<?> updateBook(int id, BookDto dto);
    ResponseEntity<?> deleteBook(int id);
    ResponseEntity<?> searchBook(String keyword);

}
