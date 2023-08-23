package com.app.library.repository;

import com.app.library.model.Book;
import com.app.library.model.Category;
import com.app.library.model.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
    Page<Loan> findByUserId(int userId , Pageable pageable);
}