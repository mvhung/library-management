package com.app.library.service.impl;

import com.app.library.dto.UserDto;
import com.app.library.exception.category.CategoryException;
import com.app.library.exception.category.user.UserException;
import com.app.library.model.User;
import com.app.library.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements com.app.library.service.IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<?> signUp(UserDto newUser) {
        User user = new User();

        BeanUtils.copyProperties(newUser, user);

        addUser(user);

        return new ResponseEntity<>(newUser,HttpStatus.OK);
    }

    public User addUser(User user) {
        String newEmail = user.getEmail();
        Optional<User> userEmailExisted = userRepository.findByEmail(newEmail);
        if (!userEmailExisted.isPresent()) {
            return userRepository.save(user);
        }
        else{
            throw new UserException("user is existed");
        }
    }

    @Override
    public ResponseEntity<?> updateUser(int id, UserDto userUpdate) {
        User user = new User();

        BeanUtils.copyProperties(userUpdate, user);

        updateUserInfor(id, user);

        user.setUserID(id);
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

    public User updateUserInfor(int id,User userUpdate){
        Optional<User> userExisted = userRepository.findById(id);
        if(userExisted.isEmpty()) {
            throw new UserException("Category id " + id + " does't exist");
        }
        try {
            User user = userExisted.get();
            user.setFullName(userUpdate.getFullName());
            user.setAddress(userUpdate.getAddress());
            user.setCreateDate(userUpdate.getCreateDate());
            user.setEmail(userUpdate.getEmail());
            user.setGroup(userUpdate.getGroup());
            user.setStatus(userUpdate.getStatus());
            user.setMobile(userUpdate.getMobile());
            user.setPassword(userUpdate.getPassword());
            user.setUsername(userUpdate.getUsername());
            user.setUpdatePassword(userUpdate.getUpdatePassword());
            return userRepository.save(user);
        }catch (Exception e){
            throw new UserException("can't update user id:" + id);
        }
    }

    @Override
    public ResponseEntity<?> deleteUser(int Id) {  return null;
    }

    @Override
    public ResponseEntity<?> getUser(int Id) {
        Optional<User> user = userRepository.findById(Id);
//        UserDto userDto;
        if(user.isPresent()){
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new User(), HttpStatus.OK);
        }


    }

    @Override
    public ResponseEntity<List<User>> listAll() {
        return null;
    }

    @Override
    public ResponseEntity<List<User>> listByGroup() {
        return null;
    }
}
