package com.themis.themis.service;

public interface TicketService {
    String generateTicket(String token);
    String validateTicket(String ticket);
    void invalidateTicket(String ticket);
}