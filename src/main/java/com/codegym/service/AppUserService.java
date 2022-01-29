package com.codegym.service;

import com.codegym.model.AppUser;
import com.codegym.repository.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AppUserService implements IAppUserService{
    @Autowired
    AppUserRepo appUserRepo;

    @Override
    public List<AppUser> getAll() {
        return (List<AppUser>) appUserRepo.findAll();
    }

    @Override
    public void save(AppUser appUser) {
        appUserRepo.save(appUser);
    }

    @Override
    public void delete(Long id) {
        appUserRepo.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepo.findByUserName(username);
        return new User(appUser.getUserName(),appUser.getPassword(),appUser.getRoles());
    }
}
