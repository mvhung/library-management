package com.app.library.controller;

import com.app.library.model.User;
import com.app.library.service.impl.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    IUserService userService;
    @RequestMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id){

        return userService.getUser(id);
    }

    @RequestMapping(value = "signup",method = RequestMethod.POST)
    public ResponseEntity<User> signUp(@RequestBody User user){
        return userService.addUser(user);
    }

    @RequestMapping(value = "update/{id}",method = RequestMethod.POST)
    public ResponseEntity<User> signUp(@PathVariable int id,@RequestBody User user){
        User newUser = userService.updateUser(id, user);
        ResponseEntity<User> ok = new ResponseEntity<>(newUser, HttpStatus.OK);
        return ok;
    }
}
