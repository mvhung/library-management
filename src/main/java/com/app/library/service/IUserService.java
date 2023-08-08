package com.app.library.service;

import com.app.library.auth.RegisterRequest;
import com.app.library.dto.UserDto;
import com.app.library.exception.object.LibraryException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService {
    public ResponseEntity<?> updateUser(int id, RegisterRequest userUpdate, MultipartFile avatarFile);
    public ResponseEntity<?> deleteUser(int Id);

    public ResponseEntity<?> getUser(int Id);
    public ResponseEntity<?> listAll();
    public ResponseEntity<?> listByGroup();

}
