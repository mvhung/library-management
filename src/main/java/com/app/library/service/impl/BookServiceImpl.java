package com.app.library.service.impl;
import com.app.library.dto.BookDto;
import com.app.library.dto.CategoryDto;
import com.app.library.exception.object.ObjectException;
import com.app.library.model.Category;
import com.app.library.repository.CategoryRepository;
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
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryServiceImpl categoryService;

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
                // Nếu sách đã tồn tại, chỉ cập nhật thông tin
                Book bookToUpdate = existingBook.get();
                bookToUpdate.setBookQuantity(bookToUpdate.getBookQuantity() + dto.getBookQuantity());
                BeanUtils.copyProperties(dto, bookToUpdate);
                bookRepository.save(bookToUpdate);
                dto.setBookId(bookToUpdate.getBookId());
            } else {
                // Nếu sách chưa tồn tại, kiểm tra danh mục trước
                Category category = dto.getCategory();
                Optional<Category> existingCategory = categoryRepository.findByCategoryName(category.getCategoryName());

                if (!existingCategory.isPresent()) {
                    // Nếu danh mục không tồn tại, tạo mới và lưu vào cơ sở dữ liệu
                    categoryRepository.save(category);
                } else {
                    // Nếu danh mục đã tồn tại, gán lại đối tượng danh mục đã lưu vào sách
                    dto.setCategory(existingCategory.get());
                }

                // Tạo mới sách và lưu vào cơ sở dữ liệu
                Book newBook = new Book();
                BeanUtils.copyProperties(dto, newBook);
                newBook = save(newBook);
                dto.setBookId(newBook.getBookId());
            }

            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log thông tin lỗi
            e.printStackTrace();
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
    public ResponseEntity<?> searchBook(String title, int publishYear, String category) {
//        ||book.getBookDescription().toLowerCase().contains(keyword.toLowerCase())
        try {
            List<Book> books = getAllBooks();
            List<Book> results =  books.stream()
                    .filter(book -> book.getBookTitle().toLowerCase().contains(title.toLowerCase())
                            && book.getBookPublishedYear() == publishYear)

                    .collect(Collectors.toList());
            if (results.size() == 0) {
                return new ResponseEntity<>("No result  ", HttpStatus.OK);
            }
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            throw new ObjectException("Can't find book have " + title);
        }
    }

}