package com.app.library.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;



import java.util.Date;
@Data
@Getter
@Setter
@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @Column(name = "lo_id")
    private int loadID;

    @Column(name = "lo_no_of_date", nullable = false)
    private int loanNoOfDate;

    @Column(name = "lo_create_date", nullable = false)
    private Date loanCreateDate;

    @ManyToOne
    @JoinColumn(name = "us_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bo_id")
    private Book book;

    public Loan(int loanNoOfDate, Date loanCreateDate) {
        this.loanNoOfDate = loanNoOfDate;
        this.loanCreateDate = loanCreateDate;
    }


    public Loan() {

    }
}
