package com.alura.forohub.service;

import com.alura.forohub.dto.LoginDTO;

public interface AuthService {

    LoginDTO login(LoginDTO loginDTO);
}
