package com.app.library.service.impl;

import java.time.LocalDateTime;
import java.util.*;

import com.app.library.payload.PagedResponse;
import com.app.library.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public ResponseEntity<?> infoLoan(int Id) {
        // Info Loan by UserId
        List<Loan> loans = loanRepository.findAll();
        List<Loan> loan = new ArrayList<Loan>();
        for (Loan loan1 : loans) {
            if (loan1.getUserId() == Id) {
                loan.add(loan1);
            }
        }
        return new ResponseEntity<>(loan, HttpStatus.OK);
    }

    // Liet ke danh sach nguoi muon va id tuong ung cua nguoi muon, kiem tra neu 2
    // nguoi giong nhau thi lay 1 nguoi
    @Override
    public ResponseEntity<?> listUserBorrowing() {
        List<Loan> loans = loanRepository.findAll();
        Set<UserBorrowing> uniqueUsers = new HashSet<>();
        for (Loan loan : loans) {
            User user = userRepository.findById(loan.getUserId())
                    .orElseThrow(() -> new RuntimeException("Cannot find user with id: " + loan.getUserId()));
            UserBorrowing userBorrowing = new UserBorrowing();
            userBorrowing.setUserId(user.getUserId());
            userBorrowing.setUserName(user.getUsername());
            userBorrowing.setFirstName(user.getFirstName());
            userBorrowing.setLastName(user.getLastName());
            uniqueUsers.add(userBorrowing);
        }
        List<UserBorrowing> users = new ArrayList<>(uniqueUsers);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    public PagedResponse<Loan> getAllLoans(int page, int size) {
        AppUtils.validatePageNumberAndSize(page, size);

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "updatedAt");

        Page<Loan> loans = loanRepository.findAll(pageable);

        List<Loan> content = loans.getNumberOfElements() == 0 ? Collections.emptyList() :loans.getContent();

        return new PagedResponse<>(content, loans.getNumber(), loans.getSize(), loans.getTotalElements(),
                loans.getTotalPages(), loans.isLast());
    }
    @Override
    public ResponseEntity<?> deleteLoan(int Id) {
        if (SecurityUtil.hasCurrentUserAnyOfAuthorities("ADMIN_PERMISSION")) {
            // Delete by Id User
            List<Loan> loans = loanRepository.findAll();
            for (Loan loan : loans) {
                if (loan.getLoanId() == Id) {
                    // From Id User, get Id Book and Quantity
                    int bookId = loan.getBookId();
                    int bookQuantity = loan.getBookQuantity();
                    // Get Book by Id
                    Book book = bookRepository.findById(bookId)
                            .orElseThrow(() -> new RuntimeException("Cannot find book with id: " + bookId));
                    // Update Book Quantity
                    book.setBookQuantity(book.getBookQuantity() + bookQuantity);
                    bookRepository.save(book);
                    // Delete Loan
                    loanRepository.deleteById(loan.getLoanId());
                }
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Access denied", HttpStatus.FORBIDDEN);
    }

    // Cap nhat lai so luong sach hoac loai sach trong truong hop muon them sach
    // hoac tra sach theo Id User
    @Override
    public ResponseEntity<?> updateLoan(int Id, LoanDto newLoanDto) {
        if (SecurityUtil.hasCurrentUserAnyOfAuthorities("ADMIN_PERMISSION")) {

            List<Loan> loans = loanRepository.findAll();
            List<Loan> loan = new ArrayList<Loan>();
            for (Loan loan1 : loans) {
                if (loan1.getUserId() == Id) {
                    loan.add(loan1);
                }
            }

            for (Loan loan1 : loan) {
                if (loan1.getLoanDueDate().isBefore(LocalDateTime.now())) {
                    throw new RuntimeException("Cannot update loan with id: " + loan1.getLoanId());
                }
            }

            for (Loan loan1 : loan) {
                if (newLoanDto.getUser().getAddress() != null) {
                    User user = userRepository.findById(loan1.getUserId())
                            .orElseThrow(() -> new RuntimeException("Cannot find user with id: " + loan1.getUserId()));
                    user.setAddress(newLoanDto.getUser().getAddress());
                    userRepository.save(user);
                    loan1.setUserAddress(newLoanDto.getUser().getAddress());
                }
            }

            for (Loan loan1 : loan) {
                for (BookDto bookDto : newLoanDto.getBooks()) {
                    if (bookDto.getBookId() == loan1.getBookId()) {
                        if (bookDto.getBookQuantity() > loan1.getBookQuantity()
                                + bookRepository.findById(bookDto.getBookId()).get().getBookQuantity()) {
                            throw new RuntimeException("Cannot loan book with id: " + bookDto.getBookId());
                        }
                        loan1.setBookQuantity(bookDto.getBookQuantity());

                    }
                }
            }

        }

        return new ResponseEntity<>("Access denied", HttpStatus.FORBIDDEN);

    }

    @Override
    public ResponseEntity<?> newLoan(List<LoanDto> newLoanDtos) {
        if (SecurityUtil.hasCurrentUserAnyOfAuthorities("ADMIN_PERMISSION")) {
            List<Loan> loans = new ArrayList<Loan>();
            for (LoanDto newLoanDto : newLoanDtos) {
                boolean flag = false;

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
                            .orElseThrow(
                                    () -> new RuntimeException("Cannot find book with id: " + bookDto.getBookId()));
                    if (bookDto.getBookQuantity() > newBook.getBookQuantity()) {
                        throw new RuntimeException("Cannot loan book with id: " + bookDto.getBookId());
                    } else {
                        flag = true;
                    }
                    newBook.setBookQuantity(newBook.getBookQuantity() - bookDto.getBookQuantity());
                    bookRepository.save(newBook);
                }

                if (flag == false) {
                    throw new RuntimeException("Cannot loan book");
                } else {
                    // Add user and books to loan
                    for (BookDto bookDto : newLoanDto.getBooks()) {
                        Loan loan = new Loan();
                        loan.setUserId(newLoanDto.getUser().getUserId());
                        
                        if (bookDto.getDayLoan() != 0) {
                            loan.setLoanDueDate(LocalDateTime.now().plusDays(bookDto.getDayLoan()));
                        } else {
                            loan.setLoanDueDate(LocalDateTime.now().plusDays(14));
                        }
                    
                        loan.setBookTitle(bookRepository.findById(bookDto.getBookId()).get().getBookTitle());
                        loan.setUserName(userRepository.findById(newLoanDto.getUser().getUserId()).get().getUsername());
                        loan.setUserAddress(
                                userRepository.findById(newLoanDto.getUser().getUserId()).get().getAddress());
                        loan.setCreatedAt(LocalDateTime.now());
                        loan.setCreatedBy(SecurityUtil.currentUser().get().getUsername());
                        loan.setBookId(bookDto.getBookId());
                        loan.setBookQuantity(bookDto.getBookQuantity());
                        loan.setBookImageLink(
                                bookRepository.findById(bookDto.getBookId()).get().getBookImageLink());
                        loans.add(loan);
                    }
                }

                flag = false;
            }
            // Lưu loans vào cơ sở dữ liệu
            loanRepository.saveAll(loans);
            return new ResponseEntity<>(loans, HttpStatus.OK);
        }
        return new ResponseEntity<>("Access denied", HttpStatus.FORBIDDEN);
    }
}
