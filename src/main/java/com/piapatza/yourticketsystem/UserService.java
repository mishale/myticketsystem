package com.piapatza.yourticketsystem;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<UserEntity> findUserById(UUID userid){
        return userRepository.findById(userid);
    }

    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }
    public List<UserEntity> findAllUsers(){
        return userRepository.findAll();
    }
    UserEntity updateUser(UserEntity user){
        return updateUser(user);
    }
}
