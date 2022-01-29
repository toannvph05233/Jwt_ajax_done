package com.codegym.controller;

import com.codegym.model.AppUser;
import com.codegym.service.IAppUserService;
import com.codegym.service.JwtService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/register")
    public void register(@RequestBody AppUser appUser) {
        String pass = passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(pass);
        userService.save(appUser);
    }

    @GetMapping("/hello")
    public String hello(){
        return "helllo";
    }
}
