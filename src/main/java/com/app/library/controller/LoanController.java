package com.app.library.controller;

import com.app.library.dto.LoanDto;
import com.app.library.model.*;
import com.app.library.payload.PagedResponse;
import com.app.library.service.impl.LoanServiceImpl;
import com.app.library.utils.AppConstants;
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
    public ResponseEntity<?> infoLoanById(@PathVariable("id") int id) {
        return loanService.infoLoan(id);
    }

    @GetMapping
    public PagedResponse<Loan> getAllLoans(
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
        return loanService.getAllLoans(page, size);
    }

    @DeleteMapping(value = "/del/{id}")
    public ResponseEntity<?> deleteLoan(@PathVariable("id") int id) {
        return loanService.deleteLoan(id);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable("id") int id, @RequestBody LoanDto newLoanDto) {
        ResponseEntity<?> loan = loanService.updateLoan(id, newLoanDto);
        if (loan == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>((Loan) loan.getBody(), HttpStatus.OK);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<?> newLoan(@RequestBody LoanDto newLoanDto) {
        ResponseEntity<?> loan = loanService.newLoan((List<LoanDto>) newLoanDto);
        return new ResponseEntity<>(loan, HttpStatus.OK);
    }

}