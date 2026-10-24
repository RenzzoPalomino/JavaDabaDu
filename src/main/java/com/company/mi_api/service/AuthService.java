package com.company.mi_api.service;

import com.company.mi_api.dto.AuthResponse;
import com.company.mi_api.dto.LoginRequest;
import com.company.mi_api.dto.RegistroRequest;
import com.company.mi_api.model.Rol;
import com.company.mi_api.model.Usuario;
import com.company.mi_api.repository.UsuarioRepository;
import com.company.mi_api.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    /**
     * Registrar nuevo usuario con contraseña hasheada y rol USER por defecto.
     * @throws IllegalArgumentException si el email ya existe
     */
    public AuthResponse registrar(RegistroRequest request) {
        log.info("Registrando nuevo usuario con email: {}", request.getEmail());

        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException(
                    "Ya existe un usuario con el email: " + request.getEmail());
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol(Rol.USER);
        usuario.setFechaCreacion(LocalDateTime.now());

        repository.save(usuario);
        log.info("Usuario registrado exitosamente: {}", usuario.getEmail());

        String token = jwtUtil.generarToken(usuario.getEmail(), usuario.getRol().name());
        return new AuthResponse(token);
    }

    /**
     * Autenticar usuario y generar token JWT.
     * @throws org.springframework.security.core.AuthenticationException si las credenciales son inválidas
     */
    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        log.info("Intento de login para email: {}", request.getEmail());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        Usuario usuario = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalStateException("Usuario autenticado pero no encontrado"));

        String token = jwtUtil.generarToken(usuario.getEmail(), usuario.getRol().name());
        log.info("Login exitoso para: {}", usuario.getEmail());

        return new AuthResponse(token);
    }
}
