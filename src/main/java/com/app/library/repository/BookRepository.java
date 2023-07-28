package com.app.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.library.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByBookTitle(String bookTitle);
    @Query("SELECT b FROM Book b WHERE LOWER(b.category.categoryName) = LOWER(:categoryName)")
    List<Book> findByCategoryName(String categoryName);

    @Query("SELECT b FROM Book b WHERE LOWER(b.publisher.publisherName) = LOWER(:publisherName)")
    List<Book> findByPublisherName(@Param("publisherName") String publisherName);
}
