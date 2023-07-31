package com.app.library.dto;

import com.app.library.model.Loan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Loan}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanDto implements Serializable {
    int loanId;
    int loanNoOfDate;
    Date loanCreateDate;
    UserDto user;
    BookDto book;
}