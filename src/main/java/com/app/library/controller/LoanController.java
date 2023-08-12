package com.app.library.controller;

import com.app.library.dto.BookDto;
import com.app.library.dto.LoanDto;
import com.app.library.dto.UserDto;
import com.app.library.model.*;
import com.app.library.service.impl.LoanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/loans")
@CrossOrigin(origins = "*")
public class LoanController {

    @Autowired
    LoanServiceImpl loanService;

    @GetMapping("/info/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable("id") int id) {
        return loanService.getLoan(id);
    }

    @GetMapping(value = "/listUserBorrowing")
    public ResponseEntity<List<User>> getUserBorrowingById() {
        return loanService.listUserBorrowing();
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteLoan(@PathVariable("id") int id) {
        loanService.deleteLoan(id);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable("id") int id, @RequestBody LoanDto newLoan, @RequestBody UserDto newUser, @RequestBody BookDto newBook) {
        ResponseEntity<?> loan = loanService.updateLoan(id, newLoan, newUser, newBook);
        if (loan == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>((Loan) loan.getBody(), HttpStatus.OK);
    }

    @PostMapping(value = "/add/{id}")
    public ResponseEntity<Loan> addLoan(@PathVariable("id") int id) {
        Loan loan = loanService.addLoan(id);
        if (loan == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loan, HttpStatus.OK);
    }
}
