package com.viniciusfk.dockerizedrestfulapi.repository;

import com.viniciusfk.dockerizedrestfulapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUser extends JpaRepository<User, Integer> {
    public User findByNameOrEmail(String name, String email);
}