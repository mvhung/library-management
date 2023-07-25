package com.app.library.service.impl;
import com.app.library.dto.BookDto;
import com.app.library.exception.object.ObjectException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import com.app.library.model.Book;
import com.app.library.repository.BookRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    public Page<Book> findAll(Pageable pageable)  {
        return bookRepository.findAll(pageable);
    }

    @Override
    public ResponseEntity<?> addBook(BookDto dto) {
        try {
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

            return new ResponseEntity<>(dto,HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ObjectException("Book is created failed ");
        }

    }

    @Override
    public ResponseEntity<?> updateBook(int id, BookDto dto) {
        Book book = new Book();
        BeanUtils.copyProperties(dto, book);

        book = update(id, book);

        //cập nhật lại id của CategoryDto và trả lại CategoryDto cho client
        dto.setBookId(book.getBookId());
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    private Book update(int id, Book book) {
        Optional<Book> existed = bookRepository.findById(id);
        if(existed.isEmpty()) {
            throw new ObjectException("Book is " + id + " does't exist");
        }

        try {
            Book existedBook= existed.get();
            existedBook.setBookTitle(book.getBookTitle());
            existedBook.setBookDescription(book.getBookDescription());
            existedBook.setBookImageLink(book.getBookImageLink());
            existedBook.setBookPublishedYear(book.getBookPublishedYear());
            existedBook.setBookQuantity(book.getBookQuantity());
            existedBook.setAuthors(book.getAuthors());
            existedBook.setPublisher(book.getPublisher());

            return bookRepository.save(existedBook);
        } catch (Exception e) {
            throw new ObjectException("Book is updated failed ");
        }
    }

    @Override
    public ResponseEntity<?> deleteBook(int id) {
        try {
            Book existed = findById(id);
            bookRepository.delete(existed);
            return new ResponseEntity<>("Book with Id " + id +" was deleted", HttpStatus.OK);
        } catch (Exception e) {
            throw new ObjectException("deleted book failed ");
        }
    }

    private Book findById(int id) {
        Optional<Book> found = bookRepository.findById(id);
        if(found.isEmpty()) {
            throw new ObjectException("Book with id " + id + " doesn't exist");
        }
        return found.get();
    }

    @Override
    public ResponseEntity<?> searchBook(String keyword) {
        try {
            List<Book> books = getAllBooks();
            List<Book> results =  books.stream()
                    .filter(book -> book.getBookTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                             book.getBookDescription().toLowerCase().contains(keyword.toLowerCase()) )
                    .collect(Collectors.toList());
            if (results.size() == 0) {
                return new ResponseEntity<>("No result for " + keyword, HttpStatus.OK);
            }
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            throw new ObjectException("Can't find book have " + keyword);
        }
    }

}