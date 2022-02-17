package com.codegym.controller;

import com.codegym.model.AppUser;
import com.codegym.service.IAppUserService;
import com.codegym.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class APIHomeController {
    @Autowired
    JwtService jwtService;

    @Autowired
    IAppUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String login(@RequestBody AppUser appUser) {
        try {

            // Tạo ra 1 đối tượng Authentication.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(appUser.getUserName(), appUser.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtService.generateTokenLogin(authentication);
            return token;
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @ExceptionHandler(Exception.class)
    public String handleError() {
        return "error";
    }

    @PostMapping("/register")
    public void register(@RequestBody AppUser appUser) {
        String pass = passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(pass);
        userService.save(appUser);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/hello")
    public String hello(){
        return "helllo";
    }

    @GetMapping("/users")
    public List<AppUser> users(){
        return userService.getAll();
    }
}
