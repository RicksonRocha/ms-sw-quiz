package com.example.msswquiz.controller;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.msswquiz.dto.UserRequestDTO;
import com.example.msswquiz.dto.UserResponseDTO;
import com.example.msswquiz.model.User;
import com.example.msswquiz.repository.UserRepository;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public Stream<UserResponseDTO> getAll() {
        Stream<UserResponseDTO> userList = userRepository.findAll().stream().map(UserResponseDTO::new);
        return userList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            UserResponseDTO responseDTO = new UserResponseDTO(user.get());
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<UserResponseDTO> saveUser(@ModelAttribute UserRequestDTO data) {
        User userData = new User(data);
        userData = userRepository.save(userData);
        UserResponseDTO responseDTO = new UserResponseDTO(userData);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @ModelAttribute UserRequestDTO data) {
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(data.name());
            user.setScore(data.score());
            userRepository.save(user);
            UserResponseDTO responseDTO = new UserResponseDTO(user);
            return ResponseEntity.status(HttpStatus.FOUND).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable Long id) {
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            userRepository.delete(user);
            UserResponseDTO responseDTO = new UserResponseDTO(user);
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
