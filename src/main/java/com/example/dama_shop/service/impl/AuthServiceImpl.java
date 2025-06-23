package com.example.dama_shop.service.impl;

import com.example.dama_shop.exception.ForbiddenException;
import com.example.dama_shop.exception.NotFoundException;
import com.example.dama_shop.security.model.MyUserDetails;
import com.example.dama_shop.dto.UserDTO;
import com.example.dama_shop.dto.mapping.UserMapper;
import com.example.dama_shop.dto.requests.LoginRequest;
import com.example.dama_shop.dto.requests.UserRequestDTO;
import com.example.dama_shop.security.jwt.JWTService;
import com.example.dama_shop.model.User;
import com.example.dama_shop.repository.UserRepository;
import com.example.dama_shop.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public String verify(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        if (authentication.isAuthenticated()){
            return jwtService.generateToken(request.getUsername());
        }else {
            throw new RuntimeException("unauthorized");
        }
    }

    @Override
    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ForbiddenException("unauthorized");
        }

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof MyUserDetails)) {
            throw new ForbiddenException("Wrong principals");
        }

        return ((MyUserDetails) principal).getId();
    }

    @Override
    public User getCurrentUser() {
        return userRepository.findById(getCurrentUserId())
                .orElseThrow(() ->{
                    log.error("User not found");
                    return new NotFoundException("User not found");
                });
    }

    @Override
    public UserDTO register(UserRequestDTO request) {
        User user = userMapper.toEntity(request);
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            log.warn("User already exists");
            throw new RuntimeException("User already exists");
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }
}
