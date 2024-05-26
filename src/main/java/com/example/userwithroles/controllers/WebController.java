package com.example.userwithroles.controllers;

import com.example.userwithroles.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    private WebService webService;

    @Autowired
    public WebController(WebService webService) {
        this.webService = webService;
    }

    @GetMapping("/userweb")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> authenticatedUser() {
        return ResponseEntity.ok(webService.authenticatedUser());
    }

    @GetMapping("/useradmin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> authenticatedAdmin() {
        return ResponseEntity.ok(webService.authenticatedAdmin());
    }

    @GetMapping("/nothing")
    public ResponseEntity<String> authenticatedNothing() {
        return ResponseEntity.ok(webService.authenticatedNothing());
    }
}
