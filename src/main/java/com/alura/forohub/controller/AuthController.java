package com.alura.forohub.controller;

import com.alura.forohub.dto.DatosJWTToken;
import com.alura.forohub.dto.LoginDTO;
import com.alura.forohub.dto.RegisterDTO;
import com.alura.forohub.dto.UserResponseDTO;
import com.alura.forohub.model.User;
import com.alura.forohub.security.AutenticacionService;
import com.alura.forohub.security.TokenService;
import com.alura.forohub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity <UserResponseDTO> register (@RequestBody RegisterDTO registerDTO){
        return ResponseEntity.ok(userService.saveUser(registerDTO));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {

        Authentication authToken = new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTToken = tokenService.generateToken((User) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok(new DatosJWTToken(JWTToken));


    }

}
