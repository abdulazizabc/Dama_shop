package com.example.dama_shop.controller;

import com.example.dama_shop.dto.UserDTO;
import com.example.dama_shop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/by-username/{username}")
    public ResponseEntity<UserDTO> findByUsername(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/update-user-info/{id}")
    public ResponseEntity<UserDTO> updateUserInfo(@PathVariable Long id,
                                                  @Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUserInfo(id, userDTO));
    }

}
