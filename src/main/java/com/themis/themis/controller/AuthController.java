package com.themis.themis.controller;

import com.themis.themis.dto.LoginRequest;
import com.themis.themis.dto.TokenResponse;
import com.themis.themis.dto.TicketResponse;
import com.themis.themis.service.AuthService;
import com.themis.themis.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final TicketService ticketService;

    public AuthController(AuthService authService, TicketService ticketService) {
        this.authService = authService;
        this.ticketService = ticketService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) {
        // Autenticar usuario y generar token
        String token = authService.authenticateUser(request.getUsername(), request.getPassword());
        
        // Generar ticket asociado al token
        String ticket = ticketService.generateTicket(token);
        
        return ResponseEntity.ok(new TokenResponse(token, ticket));
    }

    @GetMapping("/validate-ticket")
    public ResponseEntity<TicketResponse> validateTicket(@RequestParam String ticket) {
        String token = ticketService.validateTicket(ticket);
        boolean isValid = token != null && authService.validateToken(token);
        String message = isValid ? "Ticket válido" : "Ticket inválido";
        return ResponseEntity.ok(new TicketResponse(isValid, message));
    }
}