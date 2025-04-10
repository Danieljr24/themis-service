package com.themis.themis.service;

import com.themis.themis.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthService(AuthenticationManager authenticationManager, 
                      JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public String authenticateUser(String username, String password) {
        // Autenticar al usuario
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password));
        
        // Establecer el contexto de seguridad
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Obtener el UserDetails
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Generar el token JWT
        return jwtUtil.generateToken(userDetails.getUsername());
    }

    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token); // Cambiado de validateJwtToken a validateToken
    }
}