package com.app.library.service;

import com.app.library.dto.UserDto;
import com.app.library.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    public ResponseEntity<?> updateUser(int Id, UserDto userUpdate);
    public ResponseEntity<?> deleteUser(int Id);

    public ResponseEntity<?> getUser(int Id);
    public ResponseEntity<?> listAll();

    public ResponseEntity<?> listByGroup();

}
