package com.viniciusfk.dockerizedrestfulapi.service;

import com.viniciusfk.dockerizedrestfulapi.model.User;
import com.viniciusfk.dockerizedrestfulapi.repository.IUser;
import com.viniciusfk.dockerizedrestfulapi.security.Token;
import com.viniciusfk.dockerizedrestfulapi.security.TokenUtil;
import com.viniciusfk.dockerizedrestfulapi.userDto.UserDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private IUser repository;
    private PasswordEncoder passwordEncoder;

    public UserService(IUser repository){
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<User> listUsers () {
        return repository.findAll();
    }

    public Optional<User> listUser (Integer id) {
        return repository.findById(id);
    }

    public User createUser (User user) {
        String encoder = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encoder);
        return repository.save(user);
    }

    public User editUser (User user) {
        String encoder = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encoder);
        return repository.save(user);
    }

    public void deleteUser(Integer id){
        repository.deleteById(id);
    }

    public Boolean validatePassword(User user) {
        String senha = repository.getReferenceById(user.getId()).getPassword();
        return passwordEncoder.matches(user.getPassword(), senha);
    }

    public Token gerarToken(UserDto user) {
        User person = repository.findByNameOrEmail(user.getName(), user.getEmail());
        if(person != null){
            boolean valid = passwordEncoder.matches(user.getPassword(), person.getPassword());

            if(valid){
                return new Token(TokenUtil.createToken(person));
            }
        }
        return null;
    }
}