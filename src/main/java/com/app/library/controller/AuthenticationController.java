package com.app.library.controller;

import com.amazonaws.auth.policy.Resource;
import com.app.library.auth.AuthenticationRequest;
import com.app.library.auth.AuthenticationResponse;
import com.app.library.exception.object.EmailAlreadyExistsException;
import com.app.library.exception.object.UserNotFoundException;
import com.app.library.model.User;
import com.app.library.repository.UserRepository;
import com.app.library.service.impl.AmazonS3Service;
import com.app.library.service.impl.AuthenticationService;
import com.app.library.auth.RegisterRequest;
import com.app.library.service.impl.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private  final LogoutService logoutService;
    private final UserRepository userRepository;
    @Autowired
    private AmazonS3Service amazonS3Service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @ModelAttribute RegisterRequest request,
            @RequestParam(value = "avatarFile", required = false) MultipartFile avatarFile
    ) {
        return ResponseEntity.ok(authenticationService.register(request, avatarFile));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);
    }
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
