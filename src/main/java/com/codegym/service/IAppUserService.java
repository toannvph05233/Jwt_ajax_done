package com.codegym.service;

import com.codegym.model.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IAppUserService extends UserDetailsService {
    List<AppUser> getAll();
    void save(AppUser appUser);
    void delete(Long id);
}
