package com.app.library.service.impl;

import com.app.library.dto.UserDto;
import com.app.library.exception.object.ObjectException;
import com.app.library.model.User;
import com.app.library.repository.UserRepository;
import com.app.library.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;



    public User addUser(User user) {
        String newEmail = user.getEmail();
        Optional<User> userEmailExisted = userRepository.findByEmail(newEmail);
        if (!userEmailExisted.isPresent()) {
            return userRepository.save(user);
        }
        else{
            throw new ObjectException("user is existed");
        }
    }




    @Override
    public ResponseEntity<?> updateUser(int id, UserDto userUpdate) {
        User user = new User();

        BeanUtils.copyProperties(userUpdate, user);

        updateUserInfor(id, user);

        user.setUserId(id);
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

    public User updateUserInfor(int id,User userUpdate){
        Optional<User> userExisted = userRepository.findById(id);
        if(userExisted.isEmpty()) {
            throw new ObjectException("User id " + id + " does't exist");
        }
        try {
            User user = userExisted.get();
            user.setFirstName(userUpdate.getFirstName());
            user.setLastName(user.getLastName());
            user.setAddress(userUpdate.getAddress());
            user.setEmail(userUpdate.getEmail());
            user.setGroup(userUpdate.getGroup());
            user.setRoleName(userUpdate.getRoleName());
            user.setPassword(userUpdate.getPassword());

            return userRepository.save(user);
        }catch (Exception e){
            throw new ObjectException("can't update user id:" + id);
        }
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
        Optional<User> user = userRepository.findById(Id);
//        UserDto userDto;
        if(user.isPresent()){
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new User(), HttpStatus.OK);
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
