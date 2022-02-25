package com.sda.patricban.electronicsgeek.controller;

import com.sda.patricban.electronicsgeek.controller.dto.UserRegistrationDto;
import com.sda.patricban.electronicsgeek.model.Order;
import com.sda.patricban.electronicsgeek.model.User;
import com.sda.patricban.electronicsgeek.model.enums.Role;
import com.sda.patricban.electronicsgeek.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/users")
@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/admin/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.addUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

    @PostMapping("/user/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("parola nu e ok");
        }
        User user = userService.getUserByEmail(userRegistrationDto.getEmail());
        if (user != null) {
            return ResponseEntity.badRequest().body("userul exista deja");
        }
        userService.saveUser(
                userRegistrationDto.getEmail(),
                userRegistrationDto.getPassword(),
                Role.CUSTOMER
        );

        return ResponseEntity.ok().body("gata am rezolvat");
    }

    @PostMapping("/admin/register")
    public ResponseEntity<Void> registerAdmin(@RequestBody @Valid UserRegistrationDto userRegistrationDto) {
        if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())) {
            return ResponseEntity.badRequest().build();
        }
        User user = userService.getUserByEmail((userRegistrationDto.getEmail()));
        if (user != null) {
            return ResponseEntity.badRequest().build();
        }
        userService.saveUser(
                userRegistrationDto.getEmail(),
                userRegistrationDto.getPassword(),
                Role.ADMIN
        );
        return ResponseEntity.ok().build();
    }


    @PutMapping("/user/update/{idUser}")
    public ResponseEntity<Void> updateUser(@RequestBody User user, @PathVariable Long idUser) {

        Optional<User> checkIfUserExists = userService.getUserByIdUser(idUser);
        if (checkIfUserExists.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/admin/deleteUser/{idUser}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long idUser){
        try{
            userService.deleteUserById(idUser);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException exception){
            return ResponseEntity.notFound().build();
        }
    }
}
