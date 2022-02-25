package com.sda.patricban.electronicsgeek.service;


import com.sda.patricban.electronicsgeek.model.User;
import com.sda.patricban.electronicsgeek.model.enums.Role;

import java.util.List;
import java.util.Optional;

public interface UserService{

    List<User> getAllUsers();

    Optional<User> getUserByIdUser(Long idUser);

    User getUserByEmail(String email);

    User addUser(User user);

    User saveUser(String email, String password, Role role);

    User updateUser(User user);


    void deleteUserById(Long idUser);


}
