package com.alura.forohub.dto;

import com.alura.forohub.model.User;

public record AutorResponseDTO(Long id, String username) {

    public AutorResponseDTO(User user) {
        this(user.getId(), user.getUsername());
}}
