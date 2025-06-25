package com.oseaghe.store.controllers;

import com.oseaghe.store.dtos.LoginRequest;
import com.oseaghe.store.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @Valid @RequestBody LoginRequest request) {
            var user= userRepository.findByEmail(request.getEmail()).orElse(null);
            if(user == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

          if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
              return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
          }

        return ResponseEntity.ok().build();
    }

}
