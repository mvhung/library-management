package com.app.library.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.app.library.model.Loan}
 */
@Data
@AllArgsConstructor
public class LoanDto implements Serializable {
    int loanNoOfDate;
    Date loanCreateDate;
}