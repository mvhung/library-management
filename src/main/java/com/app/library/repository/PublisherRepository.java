package com.app.library.repository;

import com.app.library.model.Book;
import com.app.library.model.Category;
import com.app.library.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    Optional<Publisher> findByPublisherName(String publisherName);
}
