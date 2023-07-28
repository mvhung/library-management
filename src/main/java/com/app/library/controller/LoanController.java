package com.app.library.controller;

import com.app.library.model.*;
import com.app.library.service.impl.LoanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/loans")
public class LoanController {

    @Autowired
    LoanServiceImpl loanService;

    @GetMapping("{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable("id") int id) {
        return loanService.getLoan(id);
    }

    @GetMapping(value = "listUserBorrowing")
    public ResponseEntity<List<User>> getUserBorrowingById() {
        return loanService.listUserBorrowing();
    }

    @DeleteMapping(value = "deleteLoan/{id}")
    public void deleteLoan(@PathVariable("id") int id) {
        loanService.deleteLoan(id);
    }

    @PutMapping(value = "updateLoan/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable("id") int id, @RequestBody Loan newLoan) {
        Loan loan = loanService.updateLoan(id, newLoan);
        if (loan == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loan, HttpStatus.OK);
    }

    @PostMapping(value = "addLoan/{id}")
    public ResponseEntity<Loan> addLoan(@PathVariable("id") int id) {
        Loan loan = loanService.addLoan(id);
        if (loan == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loan, HttpStatus.OK);
    }
}
