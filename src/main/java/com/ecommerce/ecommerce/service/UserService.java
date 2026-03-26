package com.ecommerce.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User findByUserId(Long id) throws Exception{
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
            return user.get();
        }
        else{
            throw new Exception("User with " + id + " was not found!");
        }
    }
    
}
