package com.rezatron.olympics.controller;

import com.rezatron.olympics.dto.UserDto;
import com.rezatron.olympics.service.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "")
    public ResponseEntity<List<UserDto>> dashboard() {
        List<UserDto> users = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(
            @RequestBody
            UserDto user) {
        UserDto _user = userService.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(_user);
    }

}
