package com.app.library.model;

import jakarta.persistence.*;
import lombok.*;


import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lo_id")
    private int loadId;

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



}
