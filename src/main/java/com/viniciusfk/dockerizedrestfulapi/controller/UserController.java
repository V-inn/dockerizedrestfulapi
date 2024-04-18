package com.viniciusfk.dockerizedrestfulapi.controller;

import com.viniciusfk.dockerizedrestfulapi.model.User;
import com.viniciusfk.dockerizedrestfulapi.security.Token;
import com.viniciusfk.dockerizedrestfulapi.service.UserService;
import com.viniciusfk.dockerizedrestfulapi.userDto.UserDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    private UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers () {
        return ResponseEntity.status(200).body(userService.listUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> listUser(@PathVariable Integer id){
        Optional<User> usuario = userService.listUser(id);

        if(usuario.isPresent()){
            return ResponseEntity.status(200).body(usuario);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        return ResponseEntity.status(201).body(userService.createUser(user));
    }

    @PutMapping
    public ResponseEntity<User> editUser(@Valid @RequestBody User user) {
        return ResponseEntity.status(200).body(userService.editUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/login")
    public  ResponseEntity<Token> login(@Valid @RequestBody UserDto user){
        Token token = userService.gerarToken(user);
        if(token != null){
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
