package com.app.library.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.app.library.model.Loan}
 */
@Value
public class LoanDto implements Serializable {
    int loanNoOfDate;
    Date loanCreateDate;
}