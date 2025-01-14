package com.alura.forohub.service.impl;

import com.alura.forohub.dto.LoginDTO;
import com.alura.forohub.model.User;
import com.alura.forohub.repository.UserRepository;
import com.alura.forohub.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthLoginImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginDTO login(LoginDTO loginDTO) {
        String username = loginDTO.username();
        String password = loginDTO.password();

        User user = new User();
        try {
            user = (User) userRepository.findByUsername(username);
        }catch (NullPointerException e) {
            throw new NullPointerException("El usuario no existe");
        }


        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("La contraseña es incorrecta");
        }

        return new LoginDTO(user.getUsername(), user.getPassword());
    }
}
