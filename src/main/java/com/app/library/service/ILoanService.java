package com.app.library.service;

import org.springframework.http.ResponseEntity;
import com.app.library.dto.LoanDto;

public interface ILoanService {
    public ResponseEntity<?> infoLoan(int Id);

    public ResponseEntity<?> deleteLoan(int Id);

    public ResponseEntity<?> updateLoan(int Id, LoanDto newLoanDto);

    public ResponseEntity<?> newLoan(LoanDto newLoanDto);

}