package com.codegym.controller;

import com.codegym.model.AppUser;
import com.codegym.model.Role;
import com.codegym.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    IAppUserService userService;

    @GetMapping("/")
    public String home() {
        return "Home";
    }

    @GetMapping("/user")
    public String user() {
        return "User";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Admin";
    }
// @GetMapping("/register")
//    public String register() {
//        return "Register";
//    }
//
//    @PostMapping("/register")
//    public String register(@ModelAttribute AppUser appUser) {
//        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
//
//        List<Role> list = new ArrayList<>();
//        Role role = new Role();
//        role.setId(1L);
//        list.add(role);
//        appUser.setRoles(list);
//
//        userService.save(appUser);
//        return "redirect:/";
//    }
}
