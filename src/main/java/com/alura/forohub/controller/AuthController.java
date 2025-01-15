package com.alura.forohub.controller;

import com.alura.forohub.dto.DatosJWTToken;
import com.alura.forohub.dto.LoginDTO;
import com.alura.forohub.dto.RegisterDTO;
import com.alura.forohub.dto.UserResponseDTO;
import com.alura.forohub.model.User;
import com.alura.forohub.security.TokenService;
import com.alura.forohub.service.AuthService;
import com.alura.forohub.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Tag(name = "Auth", description = "Endpoints para el registro y la autenticación.")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserService userService;
    private final AuthService authService;

    @Operation(
            summary = "Crear un nuevo usuario.",
            description = "Permite a un usuario su registro en la plataforma. lo campos son username, email, password y son obligatorios."
    )
    @PostMapping("/register")
    public ResponseEntity <UserResponseDTO> register (@RequestBody @Valid RegisterDTO registerDTO, UriComponentsBuilder uriComponentsBuilder) {
        UserResponseDTO userResponseDTO = userService.saveUser(registerDTO);
        URI uri = uriComponentsBuilder
                .path("/users/{id}")
                .buildAndExpand(userResponseDTO.id())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(userResponseDTO);
    }

    @Operation(
            summary = "Logearse en la plataforma.",
            description = "Permite a un usuario su logeo en la plataforma. Los campos son username y password."
    )
    @PostMapping("/login")
    public ResponseEntity<DatosJWTToken> login(@RequestBody @Valid LoginDTO loginDTO) {

        //validamos el username y password
        LoginDTO login = authService.login(loginDTO);

        Authentication authToken = new UsernamePasswordAuthenticationToken(login.username(), loginDTO.password());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTToken = tokenService.generateToken((User) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok(new DatosJWTToken(JWTToken));


    }

}
