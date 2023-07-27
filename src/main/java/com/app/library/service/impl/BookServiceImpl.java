package com.app.library.service.impl;
import com.app.library.dto.BookDto;
import com.app.library.exception.object.ObjectException;
import com.app.library.model.Category;
import com.app.library.model.Publisher;
import com.app.library.repository.AuthorRepository;
import com.app.library.repository.CategoryRepository;
import com.app.library.repository.PublisherRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import com.app.library.model.Book;
import com.app.library.repository.BookRepository;
import org.hibernate.Hibernate;
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
    private PublisherRepository publisherRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private  CategoryServiceImpl categoryService;


    public Book save(Book book) {
        return bookRepository.save(book);
    }
    @Override
    public ResponseEntity<?> getBook(int id) {
        try {
            Book book = bookRepository.findById(id).orElseThrow(() -> new ObjectException( " "+ id));
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ObjectException("No get detail book" );
    }
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
                // Nếu sách đã tồn tại, chỉ cập nhật quantity và thông tin liên quan nếu có
                Book bookToUpdate = existingBook.get();
                bookToUpdate.setBookQuantity(bookToUpdate.getBookQuantity() + dto.getBookQuantity());

                checkExistCategory(dto,bookToUpdate);

                checkExistPublisher(dto,bookToUpdate);

                bookToUpdate = save(bookToUpdate); // Lưu cập nhật vào cơ sở dữ liệu
                BeanUtils.copyProperties(bookToUpdate,dto);
                return new ResponseEntity<>(dto, HttpStatus.CREATED);
            }

            // Tạo mới sách và lưu vào cơ sở dữ liệu
            Book newBook = new Book();
            checkExistCategory(dto,newBook);
            checkExistPublisher(dto,newBook);
            BeanUtils.copyProperties(dto, newBook);
            newBook = save(newBook);
            dto.setBookId(newBook.getBookId());
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log thông tin lỗi
            e.printStackTrace();
            throw new ObjectException("Book creation failed ");
        }
    }

    private void checkExistCategory(BookDto dto, Book book) {
        // Kiểm tra  category nếu tồn tại trong dto
        if (dto.getCategory() != null) {
            Category category = dto.getCategory();
            Optional<Category> existingCategory =categoryRepository.findByCategoryName(dto.getCategory().getCategoryName());
            if (!existingCategory.isPresent()) {
                categoryRepository.save(category);
            } else {
              dto.setCategory(existingCategory.get());
            }

        }
    }
    private void checkExistPublisher(BookDto dto, Book book) {
        // Kiểm tra  publisher nếu tồn tại trong dto
       if(dto.getPublisher() != null) {
           Publisher publisher = dto.getPublisher();
           Optional<Publisher> existingPublisher = publisherRepository.findByPublisherName(dto.getPublisher().getPublisherName());
           if (!existingPublisher.isPresent()) {
               publisherRepository.save(publisher);
           } else {
               dto.setPublisher(existingPublisher.get());
           }
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
            existedBook.setCategory(book.getCategory());
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
                    .filter(book-> book.getBookTitle().toLowerCase().contains(keyword.toLowerCase())|| book.getCategory().getCategoryName().toLowerCase().contains(keyword.toLowerCase())||
                            book.getAuthors().contains(keyword.toLowerCase()))
                    .collect(Collectors.toList());
            if (results.size() == 0) {
                return new ResponseEntity<>("No result  ", HttpStatus.OK);
            }

            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ObjectException("Can't find book have " + keyword);
        }
    }

}