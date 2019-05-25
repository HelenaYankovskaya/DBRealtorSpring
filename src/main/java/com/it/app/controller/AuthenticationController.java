package com.it.app.controller;

import com.it.app.dto.request.UserRegistrationRequestDTO;
import com.it.app.dto.response.TokenResponseDTO;
import com.it.app.model.UserRole;
import com.it.app.model.User;
import com.it.app.service.UserRoleService;
import com.it.app.service.UserService;
import com.it.app.service.security.TokenService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * A Controller for authentication
 */
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    private final UserService userService;

    private final UserRoleService userRoleService;

    private final TokenService tokenService;

    private final PasswordEncoder encoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationController(UserService userService, UserRoleService userRoleService, TokenService tokenService,
                                    PasswordEncoder encoder, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.tokenService = tokenService;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Method for authorization
     *
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/signIn")
    public TokenResponseDTO authenticateUser(@RequestParam String username, @RequestParam String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new TokenResponseDTO(tokenService.generate(authentication));
    }

    /**
     * Method for refreshing token
     *
     * @param token
     * @return token
     */
    @PostMapping("/refresh")
    public TokenResponseDTO refreshToken(@RequestParam String token) {
        return new TokenResponseDTO(tokenService.refresh(token));
    }

    /**
     * Method for registration
     * @param userRegistrationRequestDTO
     * @return  user
     */
    @PostMapping("/signUp")
    public User registerUser(@RequestBody UserRegistrationRequestDTO userRegistrationRequestDTO) {
        User user = new User();
        user.setName(userRegistrationRequestDTO.getName());
        user.setPassword(encoder.encode(userRegistrationRequestDTO.getPassword()));
        UserRole role = userRoleService.findByUserRole(userRegistrationRequestDTO.getRole());
        user.setUserRole(role);
        return userService.save(user);
    }
}

