package com.app.library.service.impl;

import java.time.LocalDateTime;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import com.app.library.dto.*;
import com.app.library.model.*;
import com.app.library.repository.*;
import com.app.library.utils.SecurityUtil;

@Service
public class LoanServiceImpl implements com.app.library.service.ILoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    // Info Loan by UserId
    @Override
    public ResponseEntity<?> infoLoan(int Id) {
        List<Loan> loans = loanRepository.findAll();
        List<Loan> loan = new ArrayList<Loan>();
        for (Loan loan1 : loans) {
            if (loan1.getUserId() == Id) {
                loan.add(loan1);
            }
        }
        return new ResponseEntity<>(loan, HttpStatus.OK);    
    }

    @Override
    public ResponseEntity<?> deleteLoan(int Id) {
        // Loan loan = loanRepository.findById(Id).orElseThrow(() -> new
        // RuntimeException("can't find loan id:" + Id));
        // Loan loan = ListAllLoans.stream().filter(l -> l.getLoanId() ==
        // Id).findFirst()
        // .orElseThrow(() -> new RuntimeException("can't find loan id:" + Id));
        // List<Book> bookList = loan.getBooks();
        // for (Book book : bookList) {
        // int bookId = book.getBookId();
        // // Lấy số lượng sách hiện tại trong bookRepository và cộng với số sách hiện
        // tại
        // // trong loan tương ứng với id
        // int currentQuantity =
        // bookRepository.findById(bookId).get().getBookQuantity();
        // int loanBookQuantity = book.getBookQuantity();
        // int totalQuantity = currentQuantity + loanBookQuantity;
        // // Cập nhật lại số lượng sách trong bookRepository
        // bookRepository.findById(bookId).get().setBookQuantity(totalQuantity);
        // }
        // loan.setBooks(null);
        // loan.setUser(null);
        // loanRepository.deleteById(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateLoan(int Id, LoanDto newLoanDto) {
        // if (SecurityUtil.hasCurrentUserAnyOfAuthorities("ADMIN_PERMISSION")) {

        //     Optional<Loan> loan = loanRepository.findById(Id);
        //     if (!loan.isPresent()) {
        //         throw new RuntimeException("Cannot find loan with id: " + Id);
        //     }

        //     Loan loan1 = loan.get();

        //     // Update by user with date
        //     loan1.setUpdatedAt(LocalDateTime.now());
        //     loan1.setUpdatedBy(SecurityUtil.currentUser().get().getUsername());

        //     User user = loan1.getUser();
        //     if (user != null) {
        //         if (newLoanDto.getUser().getUserId() == user.getUserId()) {
        //             if (newLoanDto.getUser().getAddress() != user.getAddress()) {
        //                 user.setAddress(newLoanDto.getUser().getAddress());
        //             }
        //             userRepository.save(user);
        //         }
        //     } else {
        //         user = userRepository.findById(newLoanDto.getUser().getUserId())
        //                 .orElseThrow(() -> new RuntimeException(
        //                         "Cannot find user with id: " + newLoanDto.getUser().getUserId()));
        //         loan1.setUser(user);
        //         user.setAddress(newLoanDto.getUser().getAddress());
        //         userRepository.save(user);
        //     }

            // List<Book> books = loan1.getBooks();
            // if (books != null) {
            // for (Book bookLoan : books) {
            // for (BookDto booknewLoan : newLoan.getBooks()) {
            // if (booknewLoan.getBookId() == bookLoan.getBookId()) {
            // int totalQuantity = bookLoan.getBookQuantity()
            // + bookRepository.findById(booknewLoan.getBookId()).get().getBookQuantity();
            // if (booknewLoan.getBookQuantity() < totalQuantity) {
            // bookLoan.setBookQuantity(booknewLoan.getBookQuantity());
            // bookRepository.findById(bookLoan.getBookId()).get().setBookQuantity(totalQuantity
            // - booknewLoan.getBookQuantity());
            // } else {
            // throw new RuntimeException(
            // "Cannot update book with id: " + booknewLoan.getBookId());
            // }
            // } else {
            // books.add(bookRepository.findById(booknewLoan.getBookId())
            // .orElseThrow(() -> new RuntimeException(
            // "Cannot find book with id: " + booknewLoan.getBookId())));
            // bookLoan.setBookId(booknewLoan.getBookId());
            // bookLoan.setBookQuantity(booknewLoan.getBookQuantity());
            // bookLoan.setBookTitle(booknewLoan.getBookTitle());
            // bookRepository.findById(booknewLoan.getBookId()).get().setBookQuantity(
            // bookRepository.findById(booknewLoan.getBookId()).get().getBookQuantity()
            // - booknewLoan.getBookQuantity());
            // }
            // }
            // }
            // } else {
            // List<Book> book = new ArrayList<Book>();
            // for (BookDto booknewLoan : newLoan.getBooks()) {
            // Book newBook = bookRepository.findById(booknewLoan.getBookId())
            // .orElseThrow(() -> new RuntimeException(
            // "Cannot find book with id: " + booknewLoan.getBookId()));
            // book.add(newBook);
            // }
            // loan1.setBooks(books);
            // }

        //     loanRepository.save(loan1);
        //     return new ResponseEntity<>(loan1, HttpStatus.OK);
        // }
        return null;
    }

    @Override
    public ResponseEntity<?> newLoan(LoanDto newLoanDto) {
        if (SecurityUtil.hasCurrentUserAnyOfAuthorities("ADMIN_PERMISSION")) {

            boolean flag = false;
            List<Loan> loans = new ArrayList<Loan>();

            // Set user
            User newUser = userRepository.findById(newLoanDto.getUser().getUserId())
                    .orElseThrow(() -> new RuntimeException(
                            "Cannot find user with id: " + newLoanDto.getUser().getUserId()));
            if (newLoanDto.getUser().getAddress() != null) {
                newUser.setAddress(newLoanDto.getUser().getAddress());
                userRepository.save(newUser);
            }

            // Check book quantity
            for (BookDto bookDto : newLoanDto.getBooks()) {
                Book newBook = bookRepository.findById(bookDto.getBookId())
                        .orElseThrow(() -> new RuntimeException("Cannot find book with id: " + bookDto.getBookId()));
                if (bookDto.getBookQuantity() > newBook.getBookQuantity()) {
                    throw new RuntimeException("Not enough book with id: " + bookDto.getBookId());
                } else {
                    flag = true;
                }
                newBook.setBookQuantity(newBook.getBookQuantity() - bookDto.getBookQuantity());
                bookRepository.save(newBook);
            }

            if (flag == false) {
                throw new RuntimeException("Not enough book with id: " + newLoanDto.getBooks().get(0).getBookId());
            } else {
                // Add user and books to loan
                for (BookDto bookDto : newLoanDto.getBooks()) {
                    Loan loan = new Loan();
                    loan.setUserId(newLoanDto.getUser().getUserId());
                    loan.setLoanDueDate(newLoanDto.getLoanDueDate());
                    loan.setBookTitle(bookRepository.findById(bookDto.getBookId()).get().getBookTitle());
                    loan.setUserName(userRepository.findById(newLoanDto.getUser().getUserId()).get().getUsername());
                    loan.setUserAddress(userRepository.findById(newLoanDto.getUser().getUserId()).get().getAddress());
                    loan.setCreatedAt(LocalDateTime.now());
                    loan.setCreatedBy(SecurityUtil.currentUser().get().getUsername());
                    loan.setBookId(bookDto.getBookId());
                    loan.setBookQuantity(bookDto.getBookQuantity());
                    loans.add(loan);
                }
            }

            flag = false;

            // Lưu loans vào cơ sở dữ liệu
            loanRepository.saveAll(loans);
            return new ResponseEntity<>(loans, HttpStatus.OK);
        }
        return new ResponseEntity<>("Access denied", HttpStatus.FORBIDDEN);
    }
}
