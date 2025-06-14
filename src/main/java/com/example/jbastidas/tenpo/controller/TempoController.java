package com.example.jbastidas.tenpo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempoController {

    @GetMapping("/healthCheck")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Service on");
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hola desde Spring Boot";
    }
}
