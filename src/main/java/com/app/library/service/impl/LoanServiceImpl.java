package com.app.library.service.impl;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import com.app.library.dto.BookDto;
import com.app.library.dto.LoanDto;
import com.app.library.dto.UserDto;
import com.app.library.model.*;
import com.app.library.repository.LoanRepository;


@Service
public class LoanServiceImpl implements com.app.library.service.ILoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private com.app.library.service.IUserService userService;

    @Autowired
    private com.app.library.service.IBookService bookService;

    @Override
    public ResponseEntity<Loan> getLoan(int Id) {
        Loan loan = loanRepository.findById(Id).orElseThrow(() -> new RuntimeException("can't find loan id:" + Id));
        return new ResponseEntity<>(loan, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<User>> listUserBorrowing() {
        List<Loan> loans = loanRepository.findAll();
        List<User> users = loans.stream().map(Loan::getUser).collect(Collectors.toList());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public void deleteLoan(int Id) {
        loanRepository.deleteById(Id);
    }

    @Override
    public ResponseEntity<?> updateLoan(int Id, LoanDto newLoan, UserDto newUser, BookDto newBook) {
        Optional<Loan> loan = loanRepository.findById(Id);
        if (!loan.isPresent()) {
            throw new RuntimeException("Cannot find loan with id: " + Id);
        }
        Loan loan1 = loan.get();
        loan1.setLoanNoOfDate(newLoan.getLoanNoOfDate());  
        loan1.setLoanCreateDate(newLoan.getLoanCreateDate());
        
        ResponseEntity<?> user = userService.getUser(newUser.getUserId());
        loan1.setUser((User) user.getBody());
        
        ResponseEntity<?> book = bookService.getBook(newBook.getBookId());
        loan1.setBook((Book) book.getBody());

        loan1.setLoanId(newLoan.getLoanId());  
        loanRepository.save(loan1);
        return new ResponseEntity<>(loan1, HttpStatus.OK);
    }

    @Override
    public Loan addLoan(int Id) {
        if (!loanRepository.findById(Id).isPresent()) {
            Loan newLoan = new Loan();
            newLoan.setLoanId(Id);
            return loanRepository.save(newLoan);
        }
        return null;
    }

}