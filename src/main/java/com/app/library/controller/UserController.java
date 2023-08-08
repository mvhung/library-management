package com.app.library.controller;

import com.app.library.auth.RegisterRequest;
import com.app.library.dto.UserDto;
import com.app.library.exception.object.LibraryException;
import com.app.library.model.User;
import com.app.library.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserServiceImpl userService;
    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") int id){
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable(name = "id") int id,
            @ModelAttribute RegisterRequest updateUserRequest,
            @RequestParam(value = "avatarFile", required = false) MultipartFile avatarFile
    ) {
        return userService.updateUser(id, updateUserRequest, avatarFile);

    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        return userService.deleteUser(id);
    }
}
