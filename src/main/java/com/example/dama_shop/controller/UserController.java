package com.example.dama_shop.controller;

import com.example.dama_shop.dto.UserDTO;
import com.example.dama_shop.model.User;
import com.example.dama_shop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private UserService userService;


    @GetMapping("/all-users")
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<UserDTO>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/find-user/{username}")
    public ResponseEntity<Optional<UserDTO>> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @DeleteMapping("/delete-user/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PutMapping("/update-user-info/{id}")
    public ResponseEntity<UserDTO> updateUserInfo(@PathVariable Long id,
                                                  @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUserInfo(id, userDTO));
    }

}
