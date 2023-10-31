package com.rezatron.olympics.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
@Slf4j
public class APIController {
    @GetMapping(value = "/dashboard")
    public ResponseEntity.BodyBuilder dashboard() {
        return ResponseEntity.status(HttpStatus.OK);
    }
}
