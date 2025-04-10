package com.themis.themis.controller;

import com.themis.themis.dto.TicketResponse;
import com.themis.themis.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/validate")
    public ResponseEntity<TicketResponse> validate(@RequestParam String ticket) {
        String token = ticketService.validateTicket(ticket);
        boolean isValid = token != null;
        String message = isValid ? "Ticket válido" : "Ticket inválido";
        return ResponseEntity.ok(new TicketResponse(isValid, message));
    }
}