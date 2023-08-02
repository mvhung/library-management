package com.app.library.service.impl;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import com.app.library.dto.*;
import com.app.library.model.*;
import com.app.library.repository.*;
import com.app.library.service.*;

@Service
public class LoanServiceImpl implements com.app.library.service.ILoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IBookService bookService;

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
        int oldQuantity = loan1.getBook().getBookQuantity();
        Book oldBook = loan1.getBook();

        // Update loan properties
        loan1.setLoanDueDate(newLoan.getLoanDueDate());

        // Update user and book references
        ResponseEntity<?> userResponse = userService.getUser(newUser.getUserId());
        if (userResponse.getStatusCode() == HttpStatus.OK) {
            User user = (User) userResponse.getBody();
            loan1.setUser(user);
        }

        ResponseEntity<?> bookResponse = bookService.getBook(newBook.getBookId());
        if (bookResponse.getStatusCode() == HttpStatus.OK) {
            Book book = (Book) bookResponse.getBody();
            loan1.setBook(book);
            if (book != null) {
                int updatedQuantity = book.getBookQuantity() - 1;
                if (updatedQuantity >= 0) {
                    book.setBookQuantity(updatedQuantity);
                    loan1.setBook(book);
                    bookRepository.save(book);
                    oldBook.setBookQuantity(oldQuantity + 1);
                    bookRepository.save(oldBook);
                } else {
                    throw new RuntimeException("Book quantity cannot be negative.");
                }
            } else {
                throw new RuntimeException("Book not found with id: " + newBook.getBookId());
            }
        } else {
            throw new RuntimeException("Failed to get book with id: " + newBook.getBookId());
        }

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