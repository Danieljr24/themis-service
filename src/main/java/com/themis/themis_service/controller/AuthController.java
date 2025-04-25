package com.themis.themis_service.controller;

import com.themis.themis_service.dto.LoginRequest;
import com.themis.themis_service.service.AuthService;
import com.themis.themis_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class AuthController {

     @Autowired
     private AuthService authService;

     @Autowired
     private JwtUtil jwtUtil;

     @RequestMapping("/auth")
     @PostMapping("/login")
     public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
         String token = authService.login(loginRequest);
         return ResponseEntity.ok(new (token));
     }

     @GetMapping("/validate")
     public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
         boolean isValid = jwtUtil.validateToken(token);
         return ResponseEntity.ok(new (isValid));
     }
}
