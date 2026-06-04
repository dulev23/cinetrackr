package com.cinetrackr.web;

import com.cinetrackr.dto.UserDTO;
import com.cinetrackr.model.User;
import com.cinetrackr.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.register(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getById(id));
    }

    @PostMapping("/username/{username}")
    public ResponseEntity<UserDTO> getByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.getByUsername(username));
    }
}