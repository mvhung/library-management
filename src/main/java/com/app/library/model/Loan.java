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
    private int loanId;

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

    public int getLoanID() {
        return loanId;
    }

    public void setLoanID(int loanId) {
        this.loanId = loanId;
    }

    public int getLoanOfDate() {
        return loanNoOfDate;
    }

    public void setLoanOfDate(int loanNoOfDate) {
        this.loanNoOfDate = loanNoOfDate;
    }

    public Date getLoanCreateDate() {
        return loanCreateDate;
    }

    public void setLoanCreateDate(Date loanCreateDate) {
        this.loanCreateDate = loanCreateDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Loan {" +
                "loanId=" + loanId +
                ", loanNoOfDate='" + loanNoOfDate + '\'' +
                ", loanCreateDate='" + loanCreateDate + '\'' +
                ", user='" + user + '\'' +
                ", book='" + book + '\'' +
                '}';
    }

}
