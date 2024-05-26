package com.example.userwithroles.service;

import org.springframework.stereotype.Service;

@Service
public class WebService {

    public String authenticatedUser() {
        return "AUTHENTICATED USER ONLY";
    }

    public String authenticatedAdmin() {
        return "AUTHENTICATED ADMIN ONLY";
    }

    public String authenticatedNothing() {
        return "EVERYONE CAN SEE THIS";
    }
}
