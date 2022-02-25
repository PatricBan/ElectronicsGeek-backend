package com.sda.patricban.electronicsgeek.service;

import com.sda.patricban.electronicsgeek.model.User;
import com.sda.patricban.electronicsgeek.model.enums.Role;
import com.sda.patricban.electronicsgeek.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByIdUser(Long idUser) {
        Optional<User> userFromDb = userRepository.findByIdUser(idUser);
        if(!(userFromDb.isPresent())){
            throw new IllegalArgumentException("User does not exist");
        }
        return userFromDb;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
//        Optional<User> userFromDb = userRepository.findByEmail(email);
//        if(!(userFromDb.isPresent())){
//            throw new IllegalArgumentException("User does not exist");
//        }
//        return userFromDb;
    }

    @Override
    public User saveUser(String email, String password, Role role) {
        User user = userRepository.findByEmail(email);
        if(user != null){
            throw new IllegalArgumentException("User already exists");
        }
        User user2 = new User(email, password, role);
        return userRepository.save(user2);
    }

    @Override
    public User addUser(User user) {
        Optional<User> userFromDb = userRepository.findByIdUser(user.getIdUser());
        if(userFromDb.isPresent()){
            throw new IllegalArgumentException("User already exists");
        }
      //  user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    @Override
    public User updateUser(User user) {
        Optional<User> userFromDb = userRepository.findByIdUser(user.getIdUser());
        if(userFromDb.isEmpty()){
            throw new IllegalArgumentException("User does not exist");
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long idUser) {
        Optional<User> userFromDb = userRepository.findByIdUser(idUser);
        if(!(userFromDb.isPresent())){
            throw new IllegalArgumentException("User does not exist");
        }
        userRepository.deleteById(idUser);
    }
}
