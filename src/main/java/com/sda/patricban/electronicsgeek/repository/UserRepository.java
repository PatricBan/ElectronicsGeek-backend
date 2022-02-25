package com.sda.patricban.electronicsgeek.repository;

import com.sda.patricban.electronicsgeek.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    Optional<User> findByIdUser(Long idUser);
    User findByEmail(String email);
}
