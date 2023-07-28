package com.app.library.repository;

import com.app.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Override
    Optional<Author> findById(Integer integer);
    Optional<Author> findByAuthorFullName(String authorFullName);
}
