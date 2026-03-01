package org.logonedigital.mboa_care.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.logonedigital.mboa_care.dto.LoginDto;
import org.logonedigital.mboa_care.dto.LoginResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        try {
            log.info("Login attempt for user: {}", loginDto.getEmail());
            
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()
                    )
            );

            log.info("User {} logged in successfully", loginDto.getEmail());
            
            return ResponseEntity.ok(new LoginResponseDto(
                    "Login successful",
                    authentication.getName(),
                    authentication.getAuthorities().stream()
                            .map(Object::toString)
                            .toList()
            ));
        } catch (AuthenticationException e) {
            log.error("Authentication failed for user: {}", loginDto.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponseDto("Invalid email or password", null, null));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        log.info("User logged out");
        return ResponseEntity.ok("Logout successful");
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Server is running");
    }
}
