package com.example.msswquiz.dto;

import com.example.msswquiz.model.User;

public record UserResponseDTO(Long id, String name, Long score) {
    public UserResponseDTO(User user) {
        this(user.getId(), user.getName(), user.getScore());
    }
}
