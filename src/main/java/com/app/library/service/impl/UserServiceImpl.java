package com.app.library.service.impl;

import com.app.library.auth.RegisterRequest;
import com.app.library.dto.UserDto;
import com.app.library.exception.object.*;
import com.app.library.model.User;
import com.app.library.repository.UserRepository;
import com.app.library.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AmazonS3Service amazonS3Service;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    public ResponseEntity<User> updateUser(int id, RegisterRequest updateUserRequest, MultipartFile avatarFile) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found with ID: " + id);
        }

        User existingUser = userOptional.get();
        existingUser.setFirstName(updateUserRequest.getFirstName());
        existingUser.setLastName(updateUserRequest.getLastName());
        existingUser.setUsername(updateUserRequest.getUsername());
        existingUser.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));
        existingUser.setEmail(updateUserRequest.getEmail());
        if (avatarFile != null) {
            try {
                String avatarUrl = amazonS3Service.uploadFile(avatarFile, "avatars");
                existingUser.setAvatarUrl(avatarUrl);
            } catch (IOException e) {
                throw new LibraryException(HttpStatus.BAD_REQUEST, "Failed to update avatar");
            }
        }

        // Cập nhật các trường thông tin khác theo updateUserRequest

        userRepository.save(existingUser);
        return new ResponseEntity<>(existingUser,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
                userRepository.deleteById(id);
                return new ResponseEntity<>(user,HttpStatus.OK);

        } else{
            return new ResponseEntity<>(new User(),HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> getUser(int Id) {
        try {
            User user = userRepository.findById(Id).orElseThrow();
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (Exception e) {
            throw new UserNotFoundException("No get detail user");
        }
    }

    @Override
    public ResponseEntity<?> listAll() {
        return null;
    }

    @Override
    public ResponseEntity<?> listByGroup() {
        return null;
    }
}
