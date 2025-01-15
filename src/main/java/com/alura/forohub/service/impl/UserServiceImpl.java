package com.alura.forohub.service.impl;

import com.alura.forohub.dto.RegisterDTO;
import com.alura.forohub.dto.UserResponseDTO;
import com.alura.forohub.model.User;
import com.alura.forohub.repository.UserRepository;
import com.alura.forohub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserResponseDTO saveUser(RegisterDTO registerDTO) {

        if (userRepository.existsByUsername(registerDTO.username())) {
            throw new RuntimeException("El username ya existe");
        }

        if (userRepository.existsByEmail(registerDTO.email())) {
            throw new RuntimeException("El email ya existe");
        }

        String encodedPassword = passwordEncoder.encode(registerDTO.password());
        User user = new User(registerDTO.username(), encodedPassword, registerDTO.email());
        userRepository.save(user);
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail());
    }

    @Override
    public UserResponseDTO getUser(String username) {
        return null;
    }
}
