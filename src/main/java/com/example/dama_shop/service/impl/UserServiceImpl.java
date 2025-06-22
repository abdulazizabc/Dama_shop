package com.example.dama_shop.service.impl;

import com.example.dama_shop.dto.UserDTO;
import com.example.dama_shop.dto.mapping.UserMapper;
import com.example.dama_shop.model.User;
import com.example.dama_shop.model.enums.Role;
import com.example.dama_shop.repository.UserRepository;
import com.example.dama_shop.service.AuthService;
import com.example.dama_shop.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;


    public static Role safeParseRole(String input) {
        try {
            return Role.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid role: " + input);
        }
    }



    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @Override
    public User save(User user)     {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            log.warn("User already exists");
            throw new RuntimeException("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("User does not  exist");
                    return new NotFoundException("User does not exist");
                });

        return userMapper.toDTO(user);
    }


    @Override
    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.warn("User does not exist");
                    return new NotFoundException("User does not exist");
                });

        return userMapper.toDTO(user);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(Long id) {
        User user = userRepository.findById(id)
                        .orElseThrow(() -> {
                            log.warn("User does not exist");
                            return new RuntimeException("User does not exist");
                        });

        userRepository.delete(user);

    }

    @Transactional
    @Override
    public UserDTO updateMyProfile(UserDTO userDTO) {
        Long currentUserId = authService.getCurrentUserId();
        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(userDTO.getUsername());
        user.setAge(userDTO.getAge());

        return userMapper.toDTO(userRepository.save(user));
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public UserDTO updateUserInfoByAdmin(Long userId,UserDTO userDTO) {
        User user = userRepository.findById(userId)
                        .orElseThrow(() -> {
                            log.warn("User does not exist");
                            return new RuntimeException("User does not exist");
                        });

        user.setUsername(userDTO.getUsername());
        user.setAge(userDTO.getAge());
        if (!authService.getCurrentUserId().equals(userId)) {
            user.setRole(safeParseRole(userDTO.getRole()));
        }
        log.info("Admin with id={} updated user with id={}", authService.getCurrentUserId(), userId);
        return userMapper.toDTO(userRepository.save(user));
    }

}
