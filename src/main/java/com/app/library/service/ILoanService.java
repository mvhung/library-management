package com.app.library.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.app.library.model.User;

public interface ILoanService {
    public ResponseEntity<List<User>> listUserBorrowing(int Id);
}
