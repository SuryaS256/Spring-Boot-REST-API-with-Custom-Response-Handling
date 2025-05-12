package com.example.crud.controller;

import com.example.crud.dto.ApiResponse;
import com.example.crud.entity.User;
import com.example.crud.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUser(@PathVariable Long id) {
        User user = service.getUser(id);
        ApiResponse<User> response;

        if (user == null) {
            response = new ApiResponse<>(null, "404", "No such user found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            response = new ApiResponse<>(user, "200", "User fetched successfully");
            return ResponseEntity.ok(response);
        }
    }



    @PostMapping
    public ResponseEntity<ApiResponse<Void>> saveUser(@RequestBody User user) {
        String result = service.saveUser(user);
        ApiResponse<Void> response;

        if (result.equals("User already exists")) {
            response = new ApiResponse<>(null, "409", "User already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } else {
            response = new ApiResponse<>(null, "201", "User successfully added");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
    }





    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        boolean deleted = service.deleteUser(id);
        ApiResponse<Void> response;

        if (deleted) {
            response = new ApiResponse<>(null, "200", "User successfully deleted");
            return ResponseEntity.ok(response);
        } else {
            response = new ApiResponse<>(null, "404", "User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateUser(@PathVariable Long id, @RequestBody User user) {
        boolean updated = service.updateUser(id, user);
        ApiResponse<Void> response;

        if (updated) {
            response = new ApiResponse<>(null, "200", "User updated successfully");
            return ResponseEntity.ok(response);
        } else {
            response = new ApiResponse<>(null, "404", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }




}
