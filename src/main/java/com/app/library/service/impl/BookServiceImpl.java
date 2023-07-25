package com.app.library.service.impl;
import com.app.library.dto.BookDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import com.app.library.model.Book;
import com.app.library.repository.BookRepository;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements com.app.library.service.IBookService {
    @Autowired
    private BookRepository bookRepository;

    public Book save(Book book) {
        return bookRepository.save(book);
    }
    @Override
    public ResponseEntity<?> getBook(int id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("can't find loan id:" + id));

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllBooks() {

        List<Book> books = bookRepository.findAll();
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }


    public Page<Book> findAll(Pageable pageable)  {
        return bookRepository.findAll(pageable);
    }

    @Override
    public ResponseEntity<?> addBook(BookDto dto) {
        Optional<Book> existingBook = bookRepository.findByBookTitle(dto.getBookTitle());

        if (existingBook.isPresent()) {
            // Nếu book đã tồn tại, tăng quantity lên  và lưu lại vào cơ sở dữ liệu
            Book bookToUpdate = existingBook.get();
            bookToUpdate.setBookQuantity(bookToUpdate.getBookQuantity() + dto.getBookQuantity());

            BeanUtils.copyProperties(bookToUpdate,dto);
            bookRepository.save(bookToUpdate);
            dto.setBookId(bookToUpdate.getBookId());
        }
        // Nếu book chưa tồn tại, thêm mới vào cơ sở dữ liệu
        Book newBook = new Book();
        BeanUtils.copyProperties(dto,newBook);
        newBook = save(newBook);
        dto.setBookId(newBook.getBookId());

        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Book> updateBook(int id, BookDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<Book> deleteBook(int id) {
        return null;
    }

    @Override
    public ResponseEntity<Book> searchBook(String keyword) {
        return null;
    }

}