package com.example.assessment.controller;

import com.example.assessment.entity.User;
import com.example.assessment.model.Response;
import com.example.assessment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping()
    public ResponseEntity<Response<User>> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        Response.<User>builder()
                                .data(service.saveUser(user))
                                .message("User Created")
                                .statusCode(HttpStatus.CREATED.value())
                                .status(HttpStatus.CREATED)
                                .build());
    }


}
