package com.Desbrave.Desbrave.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LogoutService {

    public void invalidarToken(String token) {
    
        SecurityContextHolder.clearContext();
       
    }
}
