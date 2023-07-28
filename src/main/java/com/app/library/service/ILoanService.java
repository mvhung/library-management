package com.app.library.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.app.library.dto.BookDto;
import com.app.library.dto.LoanDto;
import com.app.library.dto.UserDto;
import com.app.library.model.Loan;
import com.app.library.model.User;

public interface ILoanService {
    public ResponseEntity<Loan> getLoan(int Id);

    public ResponseEntity<List<User>> listUserBorrowing();

    public void deleteLoan(int Id);

    public ResponseEntity<?> updateLoan(int Id, LoanDto newLoan, UserDto newUser, BookDto newBook);

    public Loan addLoan(int Id);

}
