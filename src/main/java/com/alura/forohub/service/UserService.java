package com.alura.forohub.service;

import com.alura.forohub.dto.RegisterDTO;
import com.alura.forohub.dto.UserResponseDTO;

public interface UserService {

    UserResponseDTO saveUser(RegisterDTO registerDTO);

    UserResponseDTO getUser(String username);
}
