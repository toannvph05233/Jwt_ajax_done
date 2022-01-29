package com.codegym.repository;

import com.codegym.model.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepo extends CrudRepository<AppUser, Long> {
    AppUser findByUserName(String userName);
}
