package com.app.library.dto;

import com.app.library.model.Loan;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Loan}
 */
@Value
public class LoanDto implements Serializable {
    int loanId;
    int loanNoOfDate;
    Date loanCreateDate;
    UserDto user;
    BookRequestDto book;
}