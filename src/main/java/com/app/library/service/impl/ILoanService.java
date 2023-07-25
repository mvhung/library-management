package com.app.library.service.impl;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import com.app.library.model.*;
import com.app.library.repository.LoanRepository;


@Service
public class ILoanService implements com.app.library.service.ILoanService {
    @Autowired
    private LoanRepository loanRepository;

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
    public Loan updateLoan(int Id, Loan newLoan) {
        Optional<Loan> loan = loanRepository.findById(Id);
        if (!loan.isPresent()) {
            throw new RuntimeException("Cannot find loan with id: " + Id);
        }
        Loan loan1 = loan.get();
        loan1.setLoanOfDate(newLoan.getLoanOfDate());  
        loan1.setLoanCreateDate(newLoan.getLoanCreateDate());
        loan1.setUser(newLoan.getUser());
        loan1.setBook(newLoan.getBook());
        loan1.setLoanID(newLoan.getLoanID());  
        return loanRepository.save(loan1);
    }

    @Override
    public Loan addLoan(int Id) {
        if (!loanRepository.findById(Id).isPresent()) {
            Loan newLoan = new Loan();
            newLoan.setLoanID(Id);
            return loanRepository.save(newLoan);
        }
        return null;
    }

}