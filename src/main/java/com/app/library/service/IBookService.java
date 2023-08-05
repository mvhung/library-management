package com.app.library.service;

import com.app.library.dto.BookDto;
import com.app.library.payload.PagedResponse;
import org.springframework.http.ResponseEntity;

import com.app.library.model.Book;

import java.util.List;

public interface IBookService {
    public ResponseEntity<?> getBook(int id);
    public PagedResponse<Book> getAllBooks(int page, int size) ;
    public PagedResponse<Book> getBooksByCategoryName(String categoryName, int page, int size);
    public PagedResponse<Book> getBooksByPublisherName(String publisherName, int page, int size);
    public PagedResponse<Book> getBookByAuthorName(String publisherName, int page, int size);
    public ResponseEntity<?> addBook(BookDto dto);
    public ResponseEntity<?> updateBook(int id, BookDto dto);
    ResponseEntity<?> deleteBook(int id);
//    ResponseEntity<?> searchBook(String keyword);

    PagedResponse<Book> searchBook(String keyword, int page, int size);

}
