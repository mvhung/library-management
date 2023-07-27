package com.app.library.controller;

import com.app.library.dto.UserDto;
import com.app.library.model.User;
import com.app.library.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserServiceImpl userService;
    @RequestMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") int id){

        return userService.getUser(id);
    }

    @RequestMapping(value = "signup",method = RequestMethod.POST)
    public ResponseEntity<?> signUp(@RequestBody UserDto user){
        return userService.signUp(user);
    }

    @RequestMapping(value = "update/{id}",method = RequestMethod.PUT)
    public ResponseEntity<?> signUp(@PathVariable int id,@RequestBody UserDto user){
        return userService.updateUser(id, user);
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        return userService.deleteUser(id);
    }
}
